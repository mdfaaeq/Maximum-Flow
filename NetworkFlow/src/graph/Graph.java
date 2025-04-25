package graph;

import java.util.*;

public class Graph {
    private int numberOfNodes;
    private List<Edge>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public Graph(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        adjacencyList = new ArrayList[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int sourceNode, int destinationNode, int capacity) {
        Edge forwardEdge = new Edge(sourceNode, destinationNode, capacity);
        Edge backwardEdge = new Edge(destinationNode, sourceNode, 0);
        forwardEdge.setReverseEdge(backwardEdge);
        backwardEdge.setReverseEdge(forwardEdge);
        adjacencyList[sourceNode].add(forwardEdge);
        adjacencyList[destinationNode].add(backwardEdge);
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public List<Edge>[] getAdjacencyList() {
        return adjacencyList;
    }
}
