package BFS;

import java.util.LinkedList;
import java.util.Queue;

public class FrogJump {


    // Direction arrays for moving in the matrix
    private static final int[] ROW_DIR = {0, 0, -1, 1}; // left, right, up, down
    private static final int[] COL_DIR = {-1, 1, 0, 0};

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 0},
                {1, 0, 1, 1}
        };

        int startRow = 0;
        int startCol = 0;
        int targetRow = 3;
        int targetCol = 2;


        int minCost = findMinCost(matrix, startRow, startCol, targetRow, targetCol);
        System.out.println("Minimum cost: " + minCost);
    }

    public static int findMinCost(int[][] matrix, int startRow, int startCol, int targetRow, int targetCol) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Visited array to keep track of visited nodes
        boolean[][] visited = new boolean[m][n];

        // BFS queue: each element is {row, col, cost}
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];

            // If we reach the destination, return the cost
            if (row == targetRow && col == targetCol) {
                return distance;
            }

            // Explore all 4 possible directions
            for (int i = 0; i < 4; i++) {
                int newRow = row + ROW_DIR[i];
                int newCol = col + COL_DIR[i];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && matrix[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                    // Horizontal move
                    if (i < 2) { // left or right (horizontal)
                        queue.add(new int[]{newRow, newCol, distance});
                    } else { // Vertical move (up or down)
                        queue.add(new int[]{newRow, newCol, distance+1});
                    }
                    visited[newRow][newCol] = true;
                }
            }
        }
        // If destination is unreachable
        return -1;
    }
}


