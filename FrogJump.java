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

        int[] source = {0, 0};
        int[] destination = {3, 2};

        int minCost = findMinCost(matrix, source, destination);
        System.out.println("Minimum cost: " + minCost);
    }

    public static int findMinCost(int[][] matrix, int[] source, int[] destination) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Visited array to keep track of visited nodes
        boolean[][] visited = new boolean[rows][cols];

        // BFS queue: each element is {row, col, cost}
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(source[0], source[1], 0));
        visited[source[0]][source[1]] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // If we reach the destination, return the cost
            if (current.row == destination[0] && current.col == destination[1]) {
                return current.cost;
            }

            // Explore all 4 possible directions
            for (int i = 0; i < 4; i++) {
                int newRow = current.row + ROW_DIR[i];
                int newCol = current.col + COL_DIR[i];

                if (isValidMove(matrix, newRow, newCol, visited)) {
                    // Horizontal move
                    if (i < 2) { // left or right (horizontal)
                        queue.add(new Node(newRow, newCol, current.cost));
                    } else { // Vertical move (up or down)
                        queue.add(new Node(newRow, newCol, current.cost + 1));
                    }
                    visited[newRow][newCol] = true;
                }
            }
        }

        // If destination is unreachable
        return -1;
    }

    // Utility function to check if the move is valid
    private static boolean isValidMove(int[][] matrix, int row, int col, boolean[][] visited) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length
                && matrix[row][col] == 1 && !visited[row][col];
    }
}

// Node class to store the position and cost
class Node {
    int row, col, cost;

    Node(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }
}
