package edu.princeton.cs.algs4;

public class DoublingPredictor {
    /**
     * Predicts running time T(N) using the formula T(N) = T0 * (N / N0)^b;
     * 
     * @param n target problem size
     * @param n0 baseline problem size
     * @param t0 baseline running time T0
     * @param b exponent derived from doubling ration 2^b
     * @return predicted running time in seconds
     */
    public static double predictTime(double n, double n0, double t0, double b) {
        return t0 * Math.pow(n / n0, b);
    }

    public static void main(String[] args) {
        // Benchmark parameters: N0 = 1000, T0 = 0.5s, Doubling Ratio = 8 (2^3 => b = 3)
        double n0 = 1000.0;
        double t0 = 0.5;
        double doublingRatio = 8.0;
        double b = Math.log(doublingRatio) / Math.log(2.0); // b = lg(8) = 3

        int[] targetSizes = {1000, 2000, 4000, 8000, 16000, 32000};
        
        StdOut.printf("Baseline: N0 = %.0f, T0 = %.2fs, Doubling Ratio = %.1f (b = %.1f) \n\n", n0, t0, doublingRatio, b);
        StdOut.printf("%-10s %-20s %-20s\n", "N", "Predicted Time (s)", "Doubling Factor");
        StdOut.println("---------------------------------------------------------");

        for (int n : targetSizes) {
            double predicted = predictTime(n, n0, t0, b);
            double factor = predicted / t0;
            StdOut.printf("%-10d %-20.4f %-20.2f\n", n, predicted, factor);
        }
    }
}