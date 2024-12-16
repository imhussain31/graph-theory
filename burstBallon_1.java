public class burstBallon_1 {
    public static int maxPoints(int[] nums) {
        int n = nums.length;

        // Add boundary balloons with value 1
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        System.arraycopy(nums, 0, balloons, 1, n);

        // Memoization table
        int[][] dp = new int[n + 2][n + 2];

        return burst(dp, balloons, 0, n + 1);
    }

    private static int burst(int[][] dp, int[] balloons, int left, int right) {
        // Base case: no balloons to burst
        if (left + 1 == right) {
            return 0;
        }

        // If already computed
        if (dp[left][right] > 0) {
            return dp[left][right];
        }

        int maxPoints = 0;

        // Try bursting each balloon in the current range
        for (int i = left + 1; i < right; i++) {
            int points = balloons[left] * balloons[i] * balloons[right]
                    + burst(dp, balloons, left, i)
                    + burst(dp, balloons, i, right);
            maxPoints = Math.max(maxPoints, points);
        }

        // Store the result in the memo table
        dp[left][right] = maxPoints;
        return maxPoints;
    }

    public static void main(String[] args) {
        int[] balloons = {3, 1, 5, 8};
        System.out.println("Maximum Points: " + maxPoints(balloons)); // Output: 20
    }
}
