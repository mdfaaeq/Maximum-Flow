package graph;

public class Edge {

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
