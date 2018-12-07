package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Command.Base.AbstractWriteCommand;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Presenter.GraphPresenter;

import java.io.IOException;

public class SaveMatrixCommand extends AbstractWriteCommand {

    public SaveMatrixCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            this.prepareWriter(presenter.getFileNameToSave());
            try{
                var matrix = AdjMatrix.getMatrix();
                var len = AdjMatrix.getLength();

                for(int i = 0; i < len; i++) {
                    for(int j = 0; j < len; j++) {
                        writer.append(String.valueOf(matrix[i][j]));
                    }
                    writer.append('\n');
                }
                this.writer.flush();
                this.writer.close();
            } catch (NullPointerException e) {
                presenter.printMessage("В программу не загружен ни один граф. Загрузите граф или создайте пустой!");
                return false;
            }

        } catch (IOException e) {
            presenter.printMessage(e.getMessage());
            return false;
        }
        return true;
    }
}
///home/artemon/graph.txt