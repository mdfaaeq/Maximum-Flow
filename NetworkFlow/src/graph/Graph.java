package graph;

import java.util.*;

public class Graph {
    private int numberOfNodes;
    private List<Edge>[] neighbors;

    @SuppressWarnings("unchecked")
    public Graph(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        neighbors = new ArrayList[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++)
            neighbors[i] = new ArrayList<>();
    }

    public void addEdge(int sourceNode, int destinationNode, int capacity) {
        Edge forwardEdge = new Edge(sourceNode, destinationNode, capacity);
        Edge backwardEdge = new Edge(destinationNode, sourceNode, 0);

        forwardEdge.setBackEdge(backwardEdge);
        backwardEdge.setBackEdge(forwardEdge);

        neighbors[sourceNode].add(forwardEdge);
        neighbors[destinationNode].add(backwardEdge);
    }

    public List<Edge>[] getNeighbors() {
        return neighbors;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}