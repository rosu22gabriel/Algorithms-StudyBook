public class Binomial {
    /**
     * Computes binomial distribution probability using dynamic programming (tabulation).
     * Time Complexity: O(N * k)
     * Space Complexity: O(N * k)
     */
    public static double binomial(int N, int k, double p)
    { 
        if (N < 0 || k < 0) return 0.0;

        // dp[i][j] stores binomial(i, j, p)
        double[][] dp = new double[N + 1][k + 1];

        // Base case: 0 trials, 0 successes
        dp[0][0] = 1.0;

        for (int i = 1; i <= N; i++) {
            // Base case for 0 successes in i trials: (1 - p)^i
            dp[i][0] = (1.0 - p) * dp[i - 1][0];

            for (int j = 1; j <= k; j++) {
                dp[i][j] = (1.0 - p) * dp[i - 1][j] + p * dp[i - 1][j - 1];
            }
        }
        return dp[N][k];
    }

    public static void main(String[] args) {
        int N = 100;
        int k = 50;
        double p = 0.25;

        System.out.printf("Binomial (%d, %d, %.2f) = %e%n", N, k, p, binomial(N, k, p));
    }
}