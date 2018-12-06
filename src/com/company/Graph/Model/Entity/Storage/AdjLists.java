package com.company.Graph.Model.Entity.Storage;

import com.company.Graph.Model.Entity.Exceptions.NodeNotFoundException;
import com.company.Graph.Model.Common.Node;

import java.util.ArrayList;
import java.util.List;

public class AdjLists {
    private static List<Node> lists;

    static {
        lists = new ArrayList<>();
    }

    public static List<Node> getLists() {
        return lists;
    }

    public static void setLists(List<Node> lists) {
        AdjLists.lists = lists;
    }

    public static Node getNodeById(int id) throws NodeNotFoundException {
        if(lists.size() == 0 ) {
            throw new NodeNotFoundException("Список смежности пуст");
        }

        for(Node node : lists) {
            if(node.getId() == id) {
                return node;
            }
        }

        throw new NodeNotFoundException("Вершина не найдена");
    }

    public static void addNode(Node node) {
        if(lists.indexOf(node) < 0) {
            lists.add(node);
        }
    }

}
