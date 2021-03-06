package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;
import com.company.Graph.Model.Entity.Exceptions.NodeNotFoundException;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Presenter.GraphPresenter;

public class PrintNodeHaveAdjencyCommand extends AbstractCommand {
    public PrintNodeHaveAdjencyCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            int nodeId = presenter.getNextInt("Введите номер первой вершины: ");
            if(!isValidId(nodeId)) {
                presenter.printMessage("В графе не существует такой вершины");
                return false;
            }

            int secondNodeId = presenter.getNextInt("Введите номер второй вершины: ");
            if(!isValidId(secondNodeId)) {
                presenter.printMessage("В графе не существует такой вершины");
                return false;
            }

            if (AdjMatrix.isNodesHaveAdjency(nodeId, secondNodeId)) {
                presenter.printMessage("Вершины смежны");
            } else {
                presenter.printMessage("Вершины не смежны");
            }

        } catch (NullPointerException e) {
            presenter.printMessage("В программу не загружен ни один граф. Загрузите граф или создайте пустой!");
            return false;
        }
        return true;
    }
}
