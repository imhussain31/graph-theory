package BackTracking;

import java.util.Scanner;

public class TravelingSalesman {
    static int testCases; // Number of test cases
    static int numberOfCities; // Number of cities
    static int[][] costMatrix; // Matrix representing the cost of traveling between cities
    static boolean[] visitedCities; // Array to track visited cities
    static int minimumCost; // Stores the minimum cost of the tour

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        testCases = scanner.nextInt(); // Read the number of test cases

        for (int testCase = 0; testCase < testCases; testCase++) {
            numberOfCities = scanner.nextInt(); // Read the number of cities
            costMatrix = new int[numberOfCities][numberOfCities]; // Initialize the cost matrix
            visitedCities = new boolean[numberOfCities]; // Initialize the visited array
            minimumCost = Integer.MAX_VALUE; // Set the initial minimum cost to a large value

            // Read the cost matrix
            for (int i = 0; i < numberOfCities; i++) {
                for (int j = 0; j < numberOfCities; j++) {
                    costMatrix[i][j] = scanner.nextInt();
                }
            }

            // Start the tour from city 0
            visitedCities[0] = true; // Mark city 0 as visited
            calculateMinimumCost(0, 0, 0);

            // Print the minimum cost for this test case
            System.out.println(minimumCost);
        }

        scanner.close();
    }

    private static void calculateMinimumCost(int currentCity, int visitedCityCount, int currentCost) {
        // Base case: If all cities are visited except the starting city
        if (visitedCityCount == numberOfCities - 1) {
            // Add the cost to return to the starting city
            currentCost += costMatrix[currentCity][0];
            // Update the minimum cost
            minimumCost = Math.min(minimumCost, currentCost);
            return;
        }

        // Explore all other cities
        for (int nextCity = 1; nextCity < numberOfCities; nextCity++) {
            if (!visitedCities[nextCity]) { // If the city is not yet visited
                visitedCities[nextCity] = true; // Mark the city as visited
                calculateMinimumCost(nextCity, visitedCityCount + 1, currentCost + costMatrix[currentCity][nextCity]);
                visitedCities[nextCity] = false; // Backtrack: Mark the city as unvisited
            }
        }
    }
}
