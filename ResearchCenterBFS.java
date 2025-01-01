package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ResearchCenterBFS {
    // Direction arrays for moving in the matrix (left, right, up, down)
    private static final int[] ROW_DIR = {0, 0, -1, 1};
    private static final int[] COL_DIR = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size =  sc.nextInt();
        int n = sc.nextInt();

        // Input for the rare elements' positions
        int[][] rareElements = new int[n][2];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            rareElements[i][0] = x - 1; // Convert to 0-based indexing
            rareElements[i][1] = y - 1; // Convert to 0-based indexing
        }

        // Input for the matrix values
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        // Locations of rare elements (3 rare elements in this example)

        int minCost = findMinCost(matrix, rareElements);
        System.out.println("Minimum of the longest distance to research center: " + minCost);
    }

    public static int findMinCost(int[][] matrix, int[][] rareElements) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int minMaxDist = Integer.MAX_VALUE;

        // Iterate over every cell that has a road (1)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    // For each potential research center location, run BFS from all rare elements
                    int maxDist = bfs(matrix, rareElements, i, j);
                    // Find the minimum of the maximum distances from this location to all rare elements
                    minMaxDist = Math.min(minMaxDist, maxDist);
                }
            }
        }

        return minMaxDist;
    }

    // Perform BFS from multiple sources (rare element locations)
    private static int bfs(int[][] matrix, int[][] rareElements, int targetRow, int targetCol) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxDist = 0;

        // For each rare element, perform BFS to find the distance to (targetRow, targetCol)
        for (int[] rareElement : rareElements) {
            int srcRow = rareElement[0];
            int srcCol = rareElement[1];

            // Visited array to keep track of visited nodes
            boolean[][] visited = new boolean[m][n];

            // BFS queue: each element is {row, col, distance}
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{srcRow, srcCol, 0});
            visited[srcRow][srcCol] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                int distance = current[2];

                // If we reach the research center, calculate distance and update maxDist
                if (row== targetRow && col == targetCol) {
                    maxDist = Math.max(maxDist, distance);
                    break;  // No need to continue once we reach the target
                }

                // Explore all 4 possible directions
                for (int i = 0; i < 4; i++) {
                    int newRow = row + ROW_DIR[i];
                    int newCol = col + COL_DIR[i];

                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                            && matrix[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                        queue.add(new int[]{newRow, newCol, distance + 1});
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }

        return maxDist;
    }

}

