package graph;

// This class shows the correction from one node to another with a flow limit.
public class Edge {
    /*
    * from: The source node ID.
    * to: The target code ID.
    * capacity: The maximum amount of flow allowed through this edge.
    * flow: The current flow through the edge which starts at 0.
    * residual: This is used to reverse the flow if needed in Ford-Fulkerson (undo or adjust flows).
    * */
    int from, to, capacity, flow;
    Edge residual;

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public int remainCapacity() {
        return capacity - flow;
    }

    public void augment(int bottleNeck) {
        flow += bottleNeck;
        residual.flow -= bottleNeck;
    }

    public String toString() {
        return String.format("Edge %d -> %d | flow = %d / %d", from, to, flow, capacity);
    }
}
