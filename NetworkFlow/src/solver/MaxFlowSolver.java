package solver;

import graph.*;

import java.util.*;

public class MaxFlowSolver {
    public static int fordFulkerson(Graph graph, int startNode, int endNode) {
        int maxFlow = 0;

        System.out.println("Calculating maximum flow...");

        while (true) {
            Edge[] parentEdge = new Edge[graph.getNumberOfNodes()];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(startNode);

            while (!queue.isEmpty() && parentEdge[endNode] == null) {
                int currentNode = queue.poll();
                for (Edge edge : graph.getAdjacencyList()[currentNode]) {
                    int nextNode = edge.getDestinationNode();
                    if (edge.remainingCapacity() > 0 && parentEdge[nextNode] == null && nextNode != startNode) {
                        parentEdge[nextNode] = edge;
                        queue.offer(nextNode);
                    }
                }
            }

            if (parentEdge[endNode] == null) break;

            List<Integer> path = new ArrayList<>();
            int minResidualCapacity = Integer.MAX_VALUE;
            for (Edge edge = parentEdge[endNode]; edge != null; edge = parentEdge[edge.getSourceNode()]) {
                path.add(0, edge.getDestinationNode());
                minResidualCapacity = Math.min(minResidualCapacity, edge.remainingCapacity());
            }
            path.add(0, startNode);

            System.out.print("Augmenting path: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) System.out.print(" -> ");
            }
            System.out.println(" | Min Residual Capacity: " + minResidualCapacity);

            for (Edge edge = parentEdge[endNode]; edge != null; edge = parentEdge[edge.getSourceNode()]) {
                edge.augment(minResidualCapacity);
            }

            maxFlow += minResidualCapacity;
        }

        System.out.println("Maximum flow: " + maxFlow);
        return maxFlow;
    }
}
