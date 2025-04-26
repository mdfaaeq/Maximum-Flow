import graph.*;
import solver.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/resources/bridge_1.txt";
        File file = new File(filePath);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("File not found at: " + file.getAbsolutePath());
            // Exit the program if the file doesn't exist
            return;
        }

        Scanner scanner = new Scanner(file);
        // Number of nodes in the graph
        int numberOfNodes = scanner.nextInt();
        Graph graph = new Graph(numberOfNodes);

        // Read edges and capacities from the input file
        while (scanner.hasNextInt()) {
            int sourceNode = scanner.nextInt();
            int destinationNode = scanner.nextInt();
            int capacity = scanner.nextInt();
            graph.addEdge(sourceNode, destinationNode, capacity);
        }

        // Call fordFulkerson method with startNode as 0 and endNode as numberOfNodes - 1
        MaxFlowSolver.fordFulkerson(graph, 0, numberOfNodes - 1);
    }
}
