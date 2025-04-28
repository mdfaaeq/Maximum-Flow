package solver;

import graph.*;

import java.util.*;

public class MaxFlowSolver {

    // This method implements the Ford-Fulkerson algorithm to find maximum flow
    public static int fordFulkerson(Graph graph, int startNode, int endNode, boolean printPaths) {
        int maxFlow = 0; // Initializing the maximum flow
        Edge[] parentEdge = new Edge[graph.getNumberOfNodes()]; // Create an array for the parent edges
        boolean[] visited = new boolean[graph.getNumberOfNodes()]; // Create an array to mark the visited nodes
        Queue<Integer> queue = new LinkedList<>(); // Initializing the queue for BFS

        // Loop until none of the augmenting paths are left
        while (true) {
            Arrays.fill(parentEdge, null); // Resets the parent edge array
            Arrays.fill(visited, false); // Resets the visited array
            queue.clear(); // Clears the queue
            queue.offer(startNode); // Adds the start node to queue
            visited[startNode] = true; // Marks the start node as visited

            // Performing Breadth-First Search
            while (!queue.isEmpty() && parentEdge[endNode] == null) {
                int currentNode = queue.poll(); // Removes the node from the queue
                for (Edge edge : graph.getAdjacencyList()[currentNode]) { // Iterate over the edges
                    int nextNode = edge.getDestinationNode(); // Gets the destination node
                    // If not visited and capacity > 0
                    if (!visited[nextNode] && edge.remainingCapacity() > 0) {
                        visited[nextNode] = true; // Mark as visited
                        parentEdge[nextNode] = edge; // Set parent edge
                        queue.offer(nextNode); // Add node to queue
                    }
                }
            }

            // If no augmenting path was found
            if (parentEdge[endNode] == null) break;

            int minResidualCapacity = Integer.MAX_VALUE; // Initializing minimum residual capacity
            List<Integer> path = new ArrayList<>(); // Initializing path list

            // Trace back the path
            for (Edge edge = parentEdge[endNode]; edge != null; edge = parentEdge[edge.getSourceNode()]) {
                path.add(0, edge.getDestinationNode()); // Add destination node at the beginning
                minResidualCapacity = Math.min(minResidualCapacity, edge.remainingCapacity()); // Update minimum residual capacity
            }
            path.add(0, startNode); // Add start node to path

            // Printing the path if its required (In the main class by giving true it is possible)
            if (printPaths) {
                System.out.print("Augmenting path: ");
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i)); // Printing the node
                    if (i < path.size() - 1) System.out.print(" -> "); // Printing the arrow
                }
                System.out.println(" | Min Residual Capacity: " + minResidualCapacity);
            }

            // Updates the flows
            for (Edge edge = parentEdge[endNode]; edge != null; edge = parentEdge[edge.getSourceNode()]) {
                edge.augment(minResidualCapacity); // Augment flow
            }

            maxFlow += minResidualCapacity; // Add to maximum flow
        }

        return maxFlow; // Returns the maximum flow
    }
}
