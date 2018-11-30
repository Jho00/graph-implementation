package com.company.Graph.Model;

public class AdjMatrix {
    private static int[][] matrix;

    public static void load(int[][] adjMatrix) {
        matrix = adjMatrix;
    }

    public static int[][] getMatrix() {
        return matrix;
    }

    public static int getLength() {
        return matrix[0].length;
    }
}
