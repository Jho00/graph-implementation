package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Command.Base.AbstractLoadCommand;
import com.company.Graph.Model.Entity.Exceptions.IllegalPathToGraph;
import com.company.Graph.Presenter.GraphPresenter;

import java.io.File;
import java.io.IOException;

public class LoadGraphListsCommand extends AbstractLoadCommand {

    public LoadGraphListsCommand(GraphPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean execute() {
        try {
            this.prepeareToread();
        } catch (IllegalPathToGraph e) {
            presenter.printErrorMessage(e.getMessage());
        }

        int c;
        while(true){
            try {
                c = this.reader.read();


            } catch (IOException e) {
                return false;
            }

        }

//        return true;
    }


    private void prepeareToread() throws IllegalPathToGraph {
        this.file = new File(presenter.getPathToMatrixFile());
        this.prepareReader();
    }

}
