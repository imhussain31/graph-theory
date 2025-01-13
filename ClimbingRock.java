package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ClimbingRock {

    private static final int[] ROW_DIR = {1, -1, 0, 0};
    private static final int[] COL_DIR = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Number of rows
        int m = sc.nextInt(); // Number of columns

        // Input the matrix
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // Find the minimum difficulty level
        int result = findMinDifficulty(n, m, matrix);
        System.out.println(result);
    }

    public static int findMinDifficulty(int n, int m, int[][] matrix) {
        // Try all difficulty levels from 0 to N-1
        for (int difficulty = 0; difficulty < n; difficulty++) {
            if (canReachDestination(n, m, matrix, difficulty)) {
                return difficulty; // Return the minimum difficulty level
            }
        }
        return -1; // If the destination is not reachable
    }

    private static boolean canReachDestination(int n, int m, int[][] matrix, int difficulty) {
        // BFS queue stores {row, col}
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        // Start from the bottom-left corner
        if (matrix[n - 1][0] == 0) {
            return false; // If the starting point is blocked
        }
        queue.add(new int[]{n - 1, 0});
        visited[n - 1][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            // If we reached the destination (3)
            if (matrix[row][col] == 3) {
                return true;
            }

            // Explore all 4 possible directions: down, up, left, right
            for (int k = 0; k < 4; k++) {
                int newRow = row + ROW_DIR[k];
                int newCol = col + COL_DIR[k];

                // Check if the move is within bounds and if the cell is not visited
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && !visited[newRow][newCol]) {
                    // Check if the cell is valid (1 or 3)
                    if (matrix[newRow][newCol] == 1 || matrix[newRow][newCol] == 3) {
                        // For vertical movement, check if the row difference is <= difficulty
                        if (ROW_DIR[k] != 0 && Math.abs(newRow - row) <= difficulty) {
                            visited[newRow][newCol] = true;
                            queue.add(new int[]{newRow, newCol});
                        }
                        // Horizontal movement is always allowed between consecutive cells
                        if (ROW_DIR[k] == 0) {
                            visited[newRow][newCol] = true;
                            queue.add(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }

        return false; // If BFS completes without reaching the destination
    }
}
