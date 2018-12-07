package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Entity.Command.Base.AbstractWriteCommand;
import com.company.Graph.Model.Entity.Storage.AdjLists;
import com.company.Graph.Model.Entity.Storage.AdjMatrix;
import com.company.Graph.Presenter.GraphPresenter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class SaveListsCommand extends AbstractWriteCommand {

    public SaveListsCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            this.prepareWriter(presenter.getFileNameToSave());
            try {
                JSONObject object = new JSONObject();
                object.put("count", AdjMatrix.getLength());
                JSONArray lists = new JSONArray();

                AdjLists.getLists().forEach(item -> {
                    final int id = item.getId();
                    item.getAdjacencyList().forEach(adjItem -> {
                        JSONArray list = new JSONArray();
                        list.add(id);
                        list.add(adjItem.getNode());
                        list.add(adjItem.getWeight());
                        lists.add(list);
                    });
                });

                object.put("lists", lists);
                writer.write(object.toString());
                writer.flush();
                writer.close();

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
// /home/artemon/lists.txt