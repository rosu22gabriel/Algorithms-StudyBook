public class RelativelyPrimeArray {
    /**
     * Computes the greatest common divisor of two non-negative integers.
     */
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        return gcd(q, p % q);
    }

    public static void main(String[] args) {
        int N = 10; // Matrix dimensions N x N
        boolean[][] a = new boolean[N][N];

        // Populate array: a[i][j] is true if gcd(i, j) == 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = (gcd(i, j) == 1);
            }
        }
    }
}