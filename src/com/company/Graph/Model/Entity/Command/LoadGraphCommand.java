package com.company.Graph.Model.Entity.Command;


import com.company.Graph.Model.AdjMatrix;
import com.company.Graph.Model.Entity.Exceptions.IllegalPathToGraph;
import com.company.Graph.Presenter.GraphPresenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadGraphCommand extends AbstractCommand {
    private GraphPresenter presenter;

    private File file;
    private FileReader reader;
    private List<Integer> lineOfMatrix;
    private int[][] gettedMatrix;
    private boolean isFirstLine;
    private int lastIndex;


    public LoadGraphCommand(GraphPresenter presenter)  {
        this.presenter = presenter;
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



        return true;
    }

    private void prepeareToread() throws IllegalPathToGraph {
        file = new File(presenter.getPathToFile());
        if(!this.isValidPath(this.file)) {
            throw new IllegalPathToGraph("Путь до файла неверный");
        }

        try {
            this.reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Всегда существует
        }

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

    private boolean isValidPath(File file) {
        return file.exists();
    }
}
