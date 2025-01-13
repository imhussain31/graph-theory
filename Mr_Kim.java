package BackTracking;

import java.util.Scanner;

import java.util.Scanner;

public class Mr_Kim {
    static int[] x;
    static int[] y;
    static int nCustomers;
    static int minDis;

    // Calculate Manhattan distance
    static int dist(int i, int j) {
        return Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);
    }

    // Backtracking to find the optimal path
    static void optimalPath(int currentLocation, boolean[] visited, int visitedCount, int currentDistance) {
        if (visitedCount == nCustomers) {
            // Add distance to the destination and update the minimum distance
            minDis = Math.min(minDis, currentDistance + dist(currentLocation, nCustomers + 1));
            return;
        }
        for (int i = 1; i <= nCustomers; i++) {
            if (!visited[i]) {
                visited[i] = true;
                optimalPath(i, visited, visitedCount + 1, currentDistance + dist(currentLocation, i));
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of test cases
        int tCases = 1;
        while (tCases<=10) {
            minDis = Integer.MAX_VALUE; // Initialize minimum distance
            nCustomers = sc.nextInt(); // Number of customers

            // Allocate arrays dynamically based on the number of customers
            x = new int[nCustomers + 2];
            y = new int[nCustomers + 2];

            // Read coordinates of the destination
            x[nCustomers + 1] = sc.nextInt();
            y[nCustomers + 1] = sc.nextInt();

            // Read coordinates of the starting point
            x[0] = sc.nextInt();
            y[0] = sc.nextInt();

            // Read coordinates of customer locations
            for (int i = 1; i <= nCustomers; i++) {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
            }

            // Backtracking to find the minimum path
            boolean[] visited = new boolean[nCustomers + 2];
            optimalPath(0, visited, 0, 0);

            // Print the result for the current test case
            System.out.println("#" + tCases + " " + minDis);
            tCases++;
        }

        sc.close();
    }
}
