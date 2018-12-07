package com.company.Graph.Model.Entity.Command;


import com.company.Graph.Model.Entity.Exceptions.NodeNotFoundException;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Model.Entity.Command.Base.AbstractLoadCommand;
import com.company.Graph.Model.Entity.Exceptions.IllegalPathToGraph;
import com.company.Graph.Presenter.GraphPresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Старый метод парсинг списков смежности, не используется сейчас
 */
public class LoadGraphMatrixCommand extends AbstractLoadCommand {

    private List<Integer> lineOfMatrix;
    private int[][] gettedMatrix;
    private boolean isFirstLine;
    private int lastIndex;


    public LoadGraphMatrixCommand(GraphPresenter presenter)  {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            this.prepeareToread();
        } catch (IllegalPathToGraph e) {
            presenter.printErrorMessage(e.getMessage());
            return false;
        }

        int c;
        while(true){
            try {
                c = this.reader.read();
                if(c != ' ' &&  c!= '\n') {
                    this.lineOfMatrix.add(Character.getNumericValue(c));
                }
                if (c == '\n') {
                    if(isFirstLine) {
                        this.createMatrixArray(this.lineOfMatrix.size());
                        this.isFirstLine = false;
                    }
                    this.createNextLine();
                    this.lineOfMatrix = new ArrayList<>();
                }
                if (c == -1) {
                    AdjMatrix.load(this.gettedMatrix);
                    break;
                }
            } catch (IOException e) {
                return false;
            }

        }

        try {
            AdjMatrix.replicateToLists();
        } catch (NodeNotFoundException e) {
            presenter.printErrorMessage(e.getMessage());
        }

        return true;
    }

    private void prepeareToread() throws IllegalPathToGraph {
        this.file = new File(presenter.getPathToMatrixFile());

        this.prepareReader();

        this.lineOfMatrix = new ArrayList<>();
        this.isFirstLine = true;
        this.lastIndex = 0;
    }

    private void createMatrixArray(int len) {
        this.gettedMatrix = new int[len][len];
    }

    private void createNextLine() {
        final int[] i = {0};
        this.lineOfMatrix.forEach(item -> {
            this.gettedMatrix[this.lastIndex][i[0]] = item;
            i[0]++;
        });
        this.lastIndex++;
    }

}
