package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Common.Node;
import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;
import com.company.Graph.Model.Entity.Storage.AdjLists;
import com.company.Graph.Presenter.GraphPresenter;

public class AddNodeCommand extends AbstractCommand {
    public AddNodeCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            var lists = AdjLists.getLists();
            int size = lists.size();
            lists.add(new Node(size + 1));
            AdjLists.replicateToMatrix();
        } catch (NullPointerException e) {
            presenter.printMessage("В программу не загружен ни один граф. Загрузите граф или создайте пустой!");
            return false;
        }

        return true;
    }
}
