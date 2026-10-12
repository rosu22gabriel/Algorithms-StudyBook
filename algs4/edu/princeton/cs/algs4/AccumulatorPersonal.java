package edu.princeton.cs.algs4;

public class AccumulatorPersonal {
    private double m;
    private double s;
    private int N; 

    /**
     * Adds a new data value to the accumulator stream.
     * Uses Welford's algorithm for computing runnng mean and variance.
     */
    public void addDataValue(double x) {
        N++;
        s = s + 1.0 * (N - 1) / N * (x - m) * (x - m);
        m = m + (x - m) / N;
    }

    /**
     * Returns the sample mean.
     */
    public double mean() {
        return m;
    }

    /**
     * Returns the sample variance.
     */
    public double var() {
        if (N <= 1) return Double.NaN;
        return s / (N - 1);
    }

    /**
     * Returns the sample standard deviation.
     */
    public double stddev() {
        return Math.sqrt(this.var());
    }

    public int count() {
        return N;
    }

    @Override 
    public String toString() {
        return String.format("N = %d, Mean = %.5f, Variance = %.5f, StdDev = %.5f",
            N, mean(), var(), stddev()
        );
    }
        
        /**
         * Main test client comparing streaming accumulator against two-pass standard formula.
         */
    public static void main(String[] args) {
        Accumulator acc = new Accumulator();
        double[] data = {10.0, 12.0, 23.0, 23.0, 16.0, 23.0, 21.0, 16.0};

        for (double val : data) {
            acc.addDataValue(val);
        }

        StdOut.println("Streaming Accumulator Output:");
        StdOut.println(acc);

        // Verification using standard two-pass calculation
        double sum = 0;
        for (double val : data) {
            sum += val;
        }
        double expectedMean = sum / data.length;

        double sqDiffSum = 0;
        for (double val : data) {
            sqDiffSum += (val - expectedMean) * (val - expectedMean);
        }
        double expectedVar = sqDiffSum / (data.length - 1);
        double expectedStddev = Math.sqrt(expectedVar);

        StdOut.printf("\nStandard Two-Pass Exact Calculation:");
        StdOut.printf(String.format("N = %d, Mean = %.5f, Variance = %.5f, StdDev = %.5f", 
            data.length, expectedMean, expectedVar, expectedStddev
        ));
    }
}
