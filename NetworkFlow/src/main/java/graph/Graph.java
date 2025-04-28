/**
 * Name: Faaeq Fazal
 * UOW ID: w2051891
 * IIT ID: 20231190
 */

package graph;

import java.util.*;

public class Graph {
    // Total number of nodes in the graph
    private int numberOfNodes;
    // Adjacency list to store edges for each node
    private List<Edge>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public Graph(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        // Initialized the adjacency list for each node
        adjacencyList = new ArrayList[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    /*
     * Adds a forward edge and a corresponding backward (reverse) edge between two nodes
     * Forward edge has the given capacity
     * Backward edge initially has 0 capacity (used for residual graph in flow algorithms)
     */
    public void addEdge(int sourceNode, int destinationNode, int capacity) {
        // Created the forward edge with the given capacity
        Edge forwardEdge = new Edge(sourceNode, destinationNode, capacity);

        // Created the backward edge with 0 capacity
        Edge backwardEdge = new Edge(destinationNode, sourceNode, 0);

        // Link the forward and backward edges to each other
        forwardEdge.setReverseEdge(backwardEdge);
        backwardEdge.setReverseEdge(forwardEdge);

        // Add the edges to the adjacency list
        adjacencyList[sourceNode].add(forwardEdge);
        adjacencyList[destinationNode].add(backwardEdge);
    }

    // Returns the number of nodes in the graph
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    // Returns the adjacency list representing the graph
    public List<Edge>[] getAdjacencyList() {
        return adjacencyList;
    }
}
