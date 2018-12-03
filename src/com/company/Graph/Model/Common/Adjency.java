package com.company.Graph.Model.Common;

public class Adjency<N, A> {
    private N node;
    private A weight;

    public Adjency(N node, A adjency) {
        this.node = node;
        this.weight = adjency;
    }

    public N getNode() {
        return node;
    }

    public A getWeight() {
        return weight;
    }
}
