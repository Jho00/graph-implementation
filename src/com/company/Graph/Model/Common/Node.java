package com.company.Graph.Model.Common;

import java.util.*;

public class Node {
    private int id;
    private List<Adjency<Integer, Integer>> adjacencyList;

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
        return id == node.getId();
    }

    @Override
    public int hashCode() {
        return id * 42;
    }

    public void addAdjacency(int id, int weight) {
        Adjency<Integer, Integer> adj = new Adjency<>(id, weight);
        this.adjacencyList.add(adj);
    }

    public List<Adjency<Integer, Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}
