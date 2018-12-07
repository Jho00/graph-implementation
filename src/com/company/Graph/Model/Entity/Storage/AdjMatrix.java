package com.company.Graph.Model.Entity.Storage;

import com.company.Graph.Model.Common.Node;
import com.company.Graph.Model.Entity.Exceptions.NodeNotFoundException;

import java.util.ArrayList;
import java.util.List;

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

    public static void replicateToLists() throws NodeNotFoundException {
        List<Node> nodes = AdjLists.getLists();
        for(int i = 1; i <= AdjMatrix.getLength(); i++) {
            nodes.add(new Node(i));
        }
        AdjLists.setLists(nodes);
        for(int i = 0; i < getLength(); i++) {
            for(int j = 0; j < getLength(); j++) {
                    int weight = matrix [i][j];
                    if(weight >= 1) {
                        Node node = AdjLists.getNodeById(i + 1);
                        node.addAdjacency(j + 1,weight);
                    }
            }
        }
    }
}
