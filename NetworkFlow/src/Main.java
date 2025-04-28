import graph.*;
import solver.*;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nDo you want to:");
            System.out.println("1. Run a specific file");
            System.out.println("2. Quit");
            System.out.print("Enter 1, or 2: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the file name (e.g., bridge_1.txt - bridge_19.txt or ladder_1.txt - ladder_20.txt): ");
                    String fileName = scanner.nextLine();
                    String filePath = "src/resources/" + fileName;
                    processFile(filePath);
                    break;
                case 2:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, or 2.");
            }
        }
    }

    private static void processFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }

            Scanner fileScanner = new Scanner(file);
            int numberOfNodes = fileScanner.nextInt();
            Graph graph = new Graph(numberOfNodes);

            while (fileScanner.hasNextInt()) {
                int sourceNode = fileScanner.nextInt();
                int destinationNode = fileScanner.nextInt();
                int capacity = fileScanner.nextInt();
                graph.addEdge(sourceNode, destinationNode, capacity);
            }
            fileScanner.close();

            // Set this to true to print augmenting paths, false to suppress them
            boolean printPaths = false;

            long startTime = System.nanoTime();
            int maxFlow = MaxFlowSolver.fordFulkerson(graph, 0, numberOfNodes - 1, printPaths);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // converting to milliseconds

            String fileName = new File(filePath).getName().replace(".txt", "").toLowerCase();
            System.out.println(fileName + " | Max Flow: " + maxFlow + " | Execution time: " + duration + " ms");
        } catch (IOException e) {
            System.out.println("Error processing " + filePath + ": " + e.getMessage());
        }
    }
}