package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Command.Base.AbstractCommand;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Presenter.GraphPresenter;

public class CountDoubleNodeCycleCommand extends AbstractCommand {
    public CountDoubleNodeCycleCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            var matrix = AdjMatrix.getMatrix();
            int count = 0;
            try {
                int len = AdjMatrix.getLength();
                for(int i = 0; i < len; i++) {
                    for (int j = 0; j < len; j++) {
                        if(i > j) {
                            if(matrix[i][j] == matrix[j][i]) {
                                count++;
                            }
                        }
                    }
                }
            }  catch (ArrayIndexOutOfBoundsException e) {
                presenter.printMessage("В программу не загружен ни один граф. Загрузите граф или создайте пустой!");
                return  true;
            }

            presenter.printMessage("Количество двухвершинных циклов: " + count);
        } catch (NullPointerException e) {
            presenter.printMessage("В программу не загружен ни один граф. Загрузите граф или создайте пустой!");
            return false;
        }

        return true;
    }
}
