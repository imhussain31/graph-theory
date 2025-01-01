package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class chessPiece_BFS {


    // Directions of movement based on the mobile piece's allowed moves
    private static final int[][] MOVES = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // Number of test cases

        for (int t = 1; t <= T; t++) {
            int N = scanner.nextInt(); // Rows
            int M = scanner.nextInt(); // Columns

            int startRow = scanner.nextInt() - 1; // Mobile piece row (0-based index)
            int startCol = scanner.nextInt() - 1; // Mobile piece column (0-based index)
            int targetRow = scanner.nextInt() - 1; // Stationary piece row (0-based index)
            int targetCol = scanner.nextInt() - 1; // Stationary piece column (0-based index)

            int result = bfs(N, M, startRow, startCol, targetRow, targetCol);

            System.out.println("Case #" + t);
            System.out.println(result);
        }

        scanner.close();
    }

    private static int bfs(int N, int M, int startRow, int startCol, int targetRow, int targetCol) {
        // If the mobile piece starts at the target position
        if (startRow == targetRow && startCol == targetCol) {
            return 0;
        }

        // Queue to store positions and their distance from the start
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        queue.add(new int[]{startRow, startCol, 0}); // {row, col, distance}
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];


            // Explore all possible moves
            for (int[] move : MOVES) {
                int newRow = row+ move[0];
                int newCol = col + move[1];

                // Check if the new position is within bounds and not visited
                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < M && !visited[newRow][newCol]) {
                    // If we reach the target position, return the distance + 1
                    if (newRow == targetRow && newCol == targetCol) {
                        return distance + 1;
                    }

                    // Otherwise, add the new position to the queue
                    queue.add(new int[]{newRow, newCol, distance + 1});
                    visited[newRow][newCol] = true;
                }
            }
        }

        // If we exhaust the queue without finding the target, it's unreachable
        return -1;
    }

}


/*      input
        2
        9 9
        3 5 2 8
        20 20
        2 3 7 9

        Output
        Case #1
        2
        Case #2
        5
        */
