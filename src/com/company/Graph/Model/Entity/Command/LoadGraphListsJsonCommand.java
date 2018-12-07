package com.company.Graph.Model.Entity.Command;

import com.company.Graph.Model.Common.Node;
import com.company.Graph.Model.Entity.Command.Base.AbstractLoadCommand;
import com.company.Graph.Model.Entity.Exceptions.IllegalPathToGraph;
import com.company.Graph.Model.Entity.Exceptions.NodeNotFoundException;
import com.company.Graph.Model.Entity.Storage.AdjLists;
import com.company.Graph.Presenter.GraphPresenter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.File;
import java.io.IOException;

public class LoadGraphListsJsonCommand extends AbstractLoadCommand {
    public LoadGraphListsJsonCommand(GraphPresenter presenter) {
        super(presenter);
    }

    @Override
    public boolean execute() {
        try {
            this.prepareToRead();
        } catch (IllegalPathToGraph e) {
            presenter.printMessage(e.getMessage());
            return false;
        }

        var parser = new JSONParser();
        try {
            var ob = (JSONObject) parser.parse(this.reader);
            long count = (long) ob.get("count");
            this.createNodes((int)count);
            var lists = (JSONArray) ob.get("lists");
            lists.forEach(item -> {
                var current = (JSONArray) item;
                try {
                    this.createNextAdj(current.get(0),  current.get(1), current.get(2));
                } catch (NodeNotFoundException e) {
                    presenter.printMessage(e.getMessage());
                }
            });
        } catch (IOException | ParseException e) {
           presenter.printMessage(e.getMessage());
        }

        AdjLists.replicateToMatrix();
        return true;
    }

    private void prepareToRead() throws IllegalPathToGraph {
        this.file = new File(presenter.getPathToListsFile());
        this.prepareReader();
    }

    private void createNodes(int count) {
        for(int i = 1; i <= count; i++) {
            AdjLists.addNode(new Node(i));
        }
    }

    private void createNextAdj(Object nodeId, Object adjId, Object weight) throws NodeNotFoundException {
        Node node = AdjLists.getNodeById((int)(long) nodeId);
        node.addAdjacency((int)(long) adjId, (int)(long) weight);
    }


}
