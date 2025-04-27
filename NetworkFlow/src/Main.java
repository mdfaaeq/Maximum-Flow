import graph.*;
import solver.*;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nDo you want to:");
            System.out.println("1. Run all files (bridge and ladder)");
            System.out.println("2. Run a specific file");
            System.out.println("3. Quit");
            System.out.print("Enter 1, 2, or 3: ");

            int choice;
            try {
                choice = Integer.parseInt(inputScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    runAllFiles();
                    break;
                case 2:
                    System.out.print("Enter the file name (e.g., bridge_1.txt or ladder_5.txt): ");
                    String fileName = inputScanner.nextLine();
                    String filePath = "src/resources/" + fileName;
                    processFile(filePath);
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    inputScanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void runAllFiles() {
        // Process bridge files
        for (int i = 1; i <= 19; i++) {
            String bridgeFile = "src/resources/bridge_" + i + ".txt";
            processFile(bridgeFile);
        }

        // Process ladder files
        for (int i = 1; i <= 20; i++) {
            String ladderFile = "src/resources/ladder_" + i + ".txt";
            processFile(ladderFile);
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

            long startTime = System.nanoTime();
            int maxFlow = MaxFlowSolver.fordFulkerson(graph, 0, numberOfNodes - 1);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // convert to milliseconds

            String fileName = new File(filePath).getName().replace(".txt", "").toLowerCase();
            System.out.println(fileName + " | Max Flow: " + maxFlow + " | Execution time: " + duration + " ms");
        } catch (IOException e) {
            System.out.println("Error processing " + filePath + ": " + e.getMessage());
        }
    }
}
