package BruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class omniousNumber {
    public static int numberOminous(int a, int b, int k, int[] ominous, int n) {
        int count = 0;

        for (int i = a; i <= b; i++) {
            int temp = i;
            int[] digitArray = new int[10];

            // Count occurrences of each digit
            while (temp > 0) {
                digitArray[temp % 10]++;
                temp /= 10;
            }

            // Count occurrences of ominous digits
            int rougeK = 0;
            for (int j = 0; j < n; j++) {
                rougeK += digitArray[ominous[j]];
            }

            // Check if the number is ominous
            if (rougeK < k) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: a, b, k
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int k = scanner.nextInt();

        // Input: Number of ominous digits
        int n = scanner.nextInt();
        int[] ominous = new int[n];
        for (int i = 0; i < n; i++) {
            ominous[i] = scanner.nextInt();
        }

        // Output the number of ominous numbers
        System.out.println(numberOminous(a, b, k, ominous, n));

        scanner.close();
    }
}
