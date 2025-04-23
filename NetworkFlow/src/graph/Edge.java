package graph;

// This class shows the correction from one node to another with a flow limit.
public class Edge {
    /*
     * sourceNode: Starting point of the edge
     * destinationNode: End point of the edge
     * maxCapacity: Maximum flow allowed through this edge
     * currentFlow: Flow currently passing through this edge
     * backEdge: The opposite edge used to undo/adjust flow
     */
    int sourceNode, destinationNode, maxCapacity, currentFlow;
    Edge backEdge;

    public Edge(int sourceNode, int destinationNode, int maxCapacity) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.maxCapacity = maxCapacity;
        this.currentFlow = 0;
    }

    public int remainingCapacity() {
        return maxCapacity - currentFlow;
    }

    public void augment(int flowAmount) {
        currentFlow += flowAmount;
        backEdge.currentFlow -= flowAmount;
    }

    @Override
    public String toString() {
        return String.format("Edge %d -> %d | flow = %d / %d", sourceNode, destinationNode, currentFlow, maxCapacity);
    }

}
