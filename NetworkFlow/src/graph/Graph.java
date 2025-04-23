package graph;

import java.util.*;

public class Graph {
    int numberOfNodes;
    List<Edge>[] neighbors;

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

        forwardEdge.backEdge = backwardEdge;
        backwardEdge.backEdge = forwardEdge;

        neighbors[sourceNode].add(forwardEdge);
        neighbors[destinationNode].add(backwardEdge);
    }

    public List<Edge>[] getNeighbors() {
        return neighbors;
    }

}