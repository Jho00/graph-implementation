package com.company.Graph.Model.Entity.Storage;

import com.company.Graph.Model.Entity.Exceptions.NodeNotFoundException;
import com.company.Graph.Model.Common.Node;

import java.util.List;

public class AdjLists {
    private static List<Node> lists;

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
        Node node = new Node(id);
        int index = lists.indexOf(node);
        if(index > 0) {
            return lists.get(index);
        }

        throw new NodeNotFoundException("Вершина не найдена");
    }

    public static void addNode(Node node) {
        if(lists.indexOf(node) < 0) {
            lists.add(node);
        }
    }
}
