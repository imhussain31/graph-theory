package BackTracking;

import java.util.*;

public class DoctorProbability {
    public static void calculateProbability(double[][] graph, int nodes, int remainingTime, int currentNode, double currentProbability, double[] probabilities) {
        if (remainingTime <= 0) {
            probabilities[currentNode] += currentProbability;
            return;
        }

        for (int nextNode = 1; nextNode <= nodes; nextNode++) {
            if (graph[currentNode][nextNode] != 0) {
                currentProbability *= graph[currentNode][nextNode];
                calculateProbability(graph, nodes, remainingTime - 10, nextNode, currentProbability, probabilities);
                currentProbability /= graph[currentNode][nextNode];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        while (testCases--!= 0) {
            int numberOfNodes = scanner.nextInt();
            int numberOfEdges = scanner.nextInt();
            int totalTime = scanner.nextInt();

            // Initialize the graph as an adjacency matrix
            double[][] matrix = new double[numberOfNodes + 1][numberOfNodes + 1];
            for (int i = 1; i <= numberOfNodes; i++) {
                for (int j = 1; j <= numberOfNodes; j++) {
                    matrix[i][j] = 0;
                }
            }

            // Read edges and probabilities
            for (int i = 0; i < numberOfEdges; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                double probability = scanner.nextDouble();
                matrix[from][to] = probability;
            }

            // Initialize the probabilities array
            double[] probabilities = new double[numberOfNodes + 1];
            for (int i = 1; i <= numberOfNodes; i++) {
                probabilities[i] = 0.0;
            }

            // Start the probability calculation from node 1
            calculateProbability(matrix, numberOfNodes, totalTime, 1, 1.0, probabilities);

            // Find the node with the maximum probability
            double maxProbability = 0.0;
            int mostProbableNode = 0;

            for (int i = 1; i <= numberOfNodes; i++) {
                if (probabilities[i] > maxProbability) {
                    maxProbability = probabilities[i];
                    mostProbableNode = i;
                }
            }

            System.out.println(mostProbableNode + " " + maxProbability);
        }

        scanner.close();
    }

}
