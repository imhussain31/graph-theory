package BruteForce;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class toggleColumn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input dimensions of the matrix
        int m = sc.nextInt();
        int n = sc.nextInt();

        // Input number of toggle operations allowed
        int k = sc.nextInt();

        // Initialize matrix
        int[][] arr = new int[m][n];

        // Input matrix values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // Map to store row patterns and their frequencies
        HashMap<String, Integer> frequency = new HashMap<>();

        // Build row patterns as strings and calculate frequency
        for (int[] row : arr) {
            StringBuilder newRow = new StringBuilder();

            for (int num : row) {
                newRow.append(num);
            }

            String rowPattern = newRow.toString();
            frequency.put(rowPattern, frequency.getOrDefault(rowPattern, 0) + 1);
        }

        int ans = 0; // Default answer for max valid rows

        // Check each unique row pattern and calculate the maximum toggles
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            String rowPattern = entry.getKey();
            int rowFrequency = entry.getValue();

            // Count the number of zeros in the row pattern
            int numZeros = 0;
            for (int i = 0; i < rowPattern.length(); i++) {
                if (rowPattern.charAt(i) == '0') {
                    numZeros++;
                }
            }

            // Check if the row can be toggled with k operations
            if (numZeros <= k && (k - numZeros) % 2 == 0) {
                ans = Math.max(ans, rowFrequency);
            }
        }

        // Print the maximum rows with valid toggling
        System.out.println(ans);
    }
}
