import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class pillerPipe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Number of elements
        int[] arr = new int[n];

        // Read the array
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Calculate total sum
        int totalSum = Arrays.stream(arr).sum();

        // If totalSum is odd, it's impossible to split into two equal sums
        if (totalSum % 2 != 0) {
            System.out.println(0);
            return;
        }

        int target = totalSum / 2;

        // DP array to check if we can make a specific sum
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // We can always make a sum of 0

        // Fill DP table
        for (int num : arr) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }

        // Find the maximum achievable equal sum
        for (int i = target; i >= 0; i--) {
            if (dp[i]) {
                System.out.println(i * 2); // Equal pillars' total height
                return;
            }
        }

        // If no valid subset found
        System.out.println(0);
    }
}
