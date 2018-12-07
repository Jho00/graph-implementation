package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;
import com.company.Graph.Model.Entity.Storage.AdjLists;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Presenter.GraphPresenter;

public class PrintNodesCountCommand extends AbstractCommand {
    public PrintNodesCountCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            presenter.printMessage("Количество вершин: " + String.valueOf(AdjLists.getLists().size()));
        } catch (NullPointerException e) {
            presenter.printMessage("В программу не загружен ни один граф. Загрузите граф или создайте пустой!");
            return false;
        }
        return true;
    }
}
