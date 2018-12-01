package com.company.Graph.Model;

import java.util.*;

public class Node {
    private int id;
    private List<Map<Integer,Double>> adjacencyList;

    public Node(int id) {
        this.id = id;
        this.adjacencyList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return id *42;
    }

    public void addAdjacency(int id, double weight) {
        Map<Integer,Double> adj = new HashMap<>();
        adj.put(id,weight);
        this.adjacencyList.add(adj);
    }

}
