package graph;

// This class shows the correction from one node to another with a flow limit.
public class Edge {
    /*
     * fromNode: Starting point of the edge
     * toNode: End point of the edge
     * maxFlow: Maximum flow allowed through this edge
     * currentFlow: Flow currently passing through this edge
     * reverseEdge: The opposite edge used to undo/adjust flow
     */
    int fromNode, toNode, maxFlow, currentFlow;
    Edge reverseEdge;

    public Edge(int fromNode, int toNode, int maxFlow) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.maxFlow = maxFlow;
        this.currentFlow = 0;
    }

    public int getRemainingFlow() {
        return maxFlow - currentFlow;
    }

    public void addFlow(int flowToAdd) {
        currentFlow += flowToAdd;
        reverseEdge.currentFlow -= flowToAdd;
    }

    @Override
    public String toString() {
        return String.format("Edge %d -> %d | flow = %d / %d", fromNode, toNode, currentFlow, maxFlow);
    }
}
