package com.company.Graph.Model;

public class AdjMatrix {
    private static double[][] matrix;

    public static void load(double[][] adjMatrix) {
        matrix = adjMatrix;
    }

    public static double[][] getMatrix() {
        return matrix;
    }

    public static int getLength() {
        return matrix[0].length;
    }
}
