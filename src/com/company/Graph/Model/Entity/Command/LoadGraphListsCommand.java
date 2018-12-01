package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Exceptions.IllegalPathToGraph;
import com.company.Graph.Presenter.GraphPresenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadGraphListsCommand extends AbstractCommand {
    private GraphPresenter presenter;
    private File file;
    private FileReader reader;

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


        return false;
    }


    private void prepeareToread() throws IllegalPathToGraph {
        file = new File(presenter.getPathToMatrixFile());
        if(!this.isValidPath(this.file)) {
            throw new IllegalPathToGraph("Путь до файла неверный");
        }

        try {
            this.reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Всегда существует
        }

    }

}
