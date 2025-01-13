package BruteForce;

import java.util.Arrays;
import java.util.Scanner;

public class crowPot {
    // Function to calculate minimum stones needed in the worst case
    public static int minCrowPotStone(int[] overflowNumbers, int n, int k) {
        // Validate input
        if (k > n || k <= 0 || n <= 0) {
            return -1; // Invalid input
        }

        // Sort the overflow values
        Arrays.sort(overflowNumbers);

        int totalStones = 0;
        int remainingPots = n;

        // Calculate the stones for the worst case
        for (int i = 0; i < k; i++) {
            totalStones += overflowNumbers[i] * remainingPots;
            remainingPots--;
        }

        return totalStones;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of pots
        int n = sc.nextInt();

        // Input overflow numbers for the pots
        int[] overflowNumbers = new int[n];
        for (int i = 0; i < n; i++) {
            overflowNumbers[i] = sc.nextInt();
        }

        // Input the number of pots to overflow
        int k = sc.nextInt();

        // Call the function and print the result
        int result = minCrowPotStone(overflowNumbers, n, k);
        System.out.println(result);

        sc.close();
    }
}

