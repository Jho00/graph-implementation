package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;
import com.company.Graph.Model.Entity.Storage.AdjLists;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Presenter.GraphPresenter;

import java.util.ArrayList;

public class CreateEmptyGraphCommand extends AbstractCommand {
    public CreateEmptyGraphCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        AdjLists.setLists(new ArrayList<>());
        AdjMatrix.load(new int[0][0]);
        return true;
    }
}
