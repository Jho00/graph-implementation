package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Common.Node;
import com.company.Graph.Model.Entity.Command.Base.AbstractLoadCommand;
import com.company.Graph.Model.Entity.Exceptions.IllegalPathToGraph;
import com.company.Graph.Model.Entity.Exceptions.NodeNotFoundException;
import com.company.Graph.Model.Entity.Storage.AdjLists;
import com.company.Graph.Presenter.GraphPresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadGraphListsCommand extends AbstractLoadCommand {
    private boolean isFirstLine;
    private List<Integer> line;

    public LoadGraphListsCommand(GraphPresenter presenter) {
        this.presenter = presenter;
        this.isFirstLine = true;
        this.line = new ArrayList<>();
    }

    @Override
    public boolean execute() {
        try {
            this.prepareToRead();
        } catch (IllegalPathToGraph e) {
            presenter.printErrorMessage(e.getMessage());
            return false;
        }

        int c;
        while(true){
            try {
                c = this.reader.read();

                if(c != ' ' &&  c!= '\n') {
                  if(isFirstLine) {
                      this.createNodes(Character.getNumericValue(c));
                  } else {
                      line.add(Character.getNumericValue(c));
                  }
                }

                if (c == '\n') {
                    if(!isFirstLine) {
                        this.parseNextLine();
                    }

                    if(isFirstLine) {
                        this.isFirstLine = false;
                    }
                }

                if (c == -1) {
                    break;
                }

            } catch (IOException e) {
                return false;
            }

        }

        presenter.printCurrnetLists();
        return true;
    }


    private void prepareToRead() throws IllegalPathToGraph {
        this.file = new File(presenter.getPathToListsFile());
        this.prepareReader();
    }

    private void createNodes(int count) {
        for(int i = 1; i <= count; i++) {
            AdjLists.addNode(new Node(i));
        }
    }

    private void parseNextLine() {
        try {
            Node node = AdjLists.getNodeById(line.get(0));
            node.addAdjacency(line.get(1), line.get(2));
            line = new ArrayList<>();
        } catch (NodeNotFoundException e) {
            presenter.printErrorMessage(e.getMessage());
        }
    }

}
