package graph;

public class Edge {
    private int sourceNode;
    private int destinationNode;
    private int capacity;
    private int currentFlow;
    private Edge reverseEdge;

    public Edge(int sourceNode, int destinationNode, int capacity) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.capacity = capacity;
        this.currentFlow = 0;
    }

    public int remainingCapacity() {
        return capacity - currentFlow;
    }

    public void augment(int minResidualCapacity) {
        this.currentFlow += minResidualCapacity;
        this.reverseEdge.currentFlow -= minResidualCapacity;
    }

    public int getSourceNode() {
        return sourceNode;
    }

    public int getDestinationNode() {
        return destinationNode;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentFlow() {
        return currentFlow;
    }

    public Edge getReverseEdge() {
        return reverseEdge;
    }

    public void setReverseEdge(Edge reverseEdge) {
        this.reverseEdge = reverseEdge;
    }

    @Override
    public String toString() {
        return String.format("Edge %d -> %d | flow = %d / %d", sourceNode, destinationNode, currentFlow, capacity);
    }
}
