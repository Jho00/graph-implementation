package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;
import com.company.Graph.Model.Entity.Exceptions.NodeNotFoundException;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Presenter.GraphPresenter;

public class DeleteAdjencyCommand extends AbstractCommand {
    public DeleteAdjencyCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            var matrix = AdjMatrix.getMatrix();

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

            matrix[nodeId - 1][secondNodeId - 1] = 0;
            matrix[secondNodeId - 1][nodeId - 1] = 0;
            AdjMatrix.load(matrix);
            AdjMatrix.replicateToLists();

        } catch (NullPointerException e) {
            presenter.printMessage("В программу не загружен ни один граф. Загрузите граф или создайте пустой!");
            return false;
        } catch (NodeNotFoundException e) {
            presenter.printMessage(e.getMessage());
            return false;
        }

        return true;
    }
}
