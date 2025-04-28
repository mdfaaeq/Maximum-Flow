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