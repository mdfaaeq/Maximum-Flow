package graph;

import java.util.*;

public class Graph {
    int numberOfNodes;
    List<Edge>[] edgesFromNode;

    @SuppressWarnings("unchecked")
    public Graph(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        edgesFromNode = new ArrayList[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            edgesFromNode[i] = new ArrayList<>();
        }
    }

    public void addEdge(int fromNode, int toNode, int maxFlow) {
        Edge forwardEdge = new Edge(fromNode, toNode, maxFlow);
        Edge reverseEdge = new Edge(toNode, fromNode, 0);
        forwardEdge.reverseEdge = reverseEdge;
        reverseEdge.reverseEdge = forwardEdge;
        edgesFromNode[fromNode].add(forwardEdge);
        edgesFromNode[toNode].add(reverseEdge);
    }

    public List<Edge>[] getEdgesFromNode() {
        return edgesFromNode;
    }
}

