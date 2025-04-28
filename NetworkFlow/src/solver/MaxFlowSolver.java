//package solver;
//
//import graph.*;
//
//import java.util.*;
//
//public class MaxFlowSolver {
//
//    /*
//     * Implements the Ford-Fulkerson algorithm to find the maximum flow
//     * from the start node to the end node in the given graph.
//     */
//    public static int fordFulkerson(Graph graph, int startNode, int endNode) {
//        // This maxFlow will store the total maximum flow from start to end
//        int maxFlow = 0;
//
//        // System.out.println("Starting to calculate the maximum flow...");
//
//        // Keep finding augmenting paths until there are none left
//        while (true) {
//            Edge[] parentEdge = new Edge[graph.getNumberOfNodes()]; // To store the path (how we reached each node)
//            Queue<Integer> queue = new LinkedList<>(); // For BFS traversal
//            queue.offer(startNode); // Start from the source node
//
//            // Perform BFS to find an augmenting path
//            while (!queue.isEmpty() && parentEdge[endNode] == null) {
//                int currentNode = queue.poll();
//                for (Edge edge : graph.getAdjacencyList()[currentNode]) {
//                    int nextNode = edge.getDestinationNode();
//                    // Visit the next node if the edge has remaining capacity
//                    // And it hasn't been visited yet
//                    if (edge.remainingCapacity() > 0 && parentEdge[nextNode] == null && nextNode != startNode) {
//                        parentEdge[nextNode] = edge; // Remember how we got to nextNode
//                        queue.offer(nextNode); // Explore nextNode later
//                    }
//                }
//            }
//
//            // If we couldn't reach the end node, we are done
//            if (parentEdge[endNode] == null) break;
//
//            // Trace back the path from end node to start node to find the minimum residual capacity
//            List<Integer> path = new ArrayList<>();
//            int minResidualCapacity = Integer.MAX_VALUE;
//            for (Edge edge = parentEdge[endNode]; edge != null; edge = parentEdge[edge.getSourceNode()]) {
//                path.add(0, edge.getDestinationNode()); // Build the path in reverse
//                minResidualCapacity = Math.min(minResidualCapacity, edge.remainingCapacity()); // Find bottleneck capacity
//            }
//            path.add(0, startNode); // Add the start node at the beginning
//
////            // Display the augmenting path found and its bottleneck (minimum residual capacity)
////            System.out.print("Augmenting path: ");
////            for (int i = 0; i < path.size(); i++) {
////                System.out.print(path.get(i));
////                if (i < path.size() - 1) System.out.print(" -> ");
////            }
////            System.out.println(" | Min Residual Capacity: " + minResidualCapacity);
//
//            // Update the capacities along the path
//            for (Edge edge = parentEdge[endNode]; edge != null; edge = parentEdge[edge.getSourceNode()]) {
//                edge.augment(minResidualCapacity); // Augment the flow along the edge
//            }
//
//            // Add the bottleneck flow to the total max flow
//            maxFlow += minResidualCapacity;
//        }
//
//        // System.out.println("Maximum flow: " + maxFlow);
//        return maxFlow; // Return the total maximum flow found
//    }
//}
package solver;

import graph.*;

import java.util.*;

public class MaxFlowSolver {

    public static int fordFulkerson(Graph graph, int startNode, int endNode) {
        int maxFlow = 0;
        Edge[] parentEdge = new Edge[graph.getNumberOfNodes()];
        boolean[] visited = new boolean[graph.getNumberOfNodes()];
        Queue<Integer> queue = new LinkedList<>();

        while (true) {
            Arrays.fill(parentEdge, null);
            Arrays.fill(visited, false);
            queue.clear();
            queue.offer(startNode);
            visited[startNode] = true;

            // BFS to find augmenting path
            while (!queue.isEmpty() && parentEdge[endNode] == null) {
                int currentNode = queue.poll();
                for (Edge edge : graph.getAdjacencyList()[currentNode]) {
                    int nextNode = edge.getDestinationNode();
                    if (!visited[nextNode] && edge.remainingCapacity() > 0) {
                        visited[nextNode] = true;
                        parentEdge[nextNode] = edge;
                        queue.offer(nextNode);
                    }
                }
            }

            if (parentEdge[endNode] == null) break;

            // Compute min residual capacity
            int minResidualCapacity = Integer.MAX_VALUE;
            for (Edge edge = parentEdge[endNode]; edge != null; edge = parentEdge[edge.getSourceNode()]) {
                minResidualCapacity = Math.min(minResidualCapacity, edge.remainingCapacity());
            }

            // Augment flow
            for (Edge edge = parentEdge[endNode]; edge != null; edge = parentEdge[edge.getSourceNode()]) {
                edge.augment(minResidualCapacity);
            }

            maxFlow += minResidualCapacity;
        }

        return maxFlow;
    }
}