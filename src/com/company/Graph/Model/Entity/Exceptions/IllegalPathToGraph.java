package com.company.Graph.Model.Entity.Exceptions;

public class IllegalPathToGraph extends Exception {
    public IllegalPathToGraph() {
    }

    public IllegalPathToGraph(String message) {
        super(message);
    }

    public IllegalPathToGraph(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPathToGraph(Throwable cause) {
        super(cause);
    }

    public IllegalPathToGraph(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
