import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ClosestPair1D {

    public static class Pair {
        public final double val1;
        public final double val2;
        public final double difference;

        public Pair(double val1, double val2) {
            this.val1 = val1;
            this.val2 = val2;
            this.difference = Math.abs(val1 - val2);
        }
    }

    /**
     * Finds the two closest values in an array of double values in O(N log N) time.
     *
     * @param a input array of double values
     * @return Pair containing the two closest numbers
     */
    public static Pair findClosestPair(double[] a) {
        if (a == null || a.length < 2) {
            throw new IllegalArgumentException("Array must contain at least 2 elements.");
        }

        double[] sorted = a.clone();
        Arrays.sort(sorted);

        double minDiff = Double.POSITIVE_INFINITY;
        double bestVal1 = sorted[0];
        double bestVal2 = sorted[1];

        for (int i = 1; i < sorted.length; i++) {
            double diff = sorted[i] - sorted[i - 1];
            if (diff < minDiff) {
                minDiff = diff;
                bestVal1 = sorted[i - 1];
                bestVal2 = sorted[i];
            }
        }

        return new Pair(bestVal1, bestVal2);
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            double[] a = in.readAllDoubles();
            Pair result = findClosestPair(a);
            StdOut.printf("Closest pair: %.4f and %.4f (difference: %.4f)\n", 
                          result.val1, result.val2, result.difference);
        } else {
            // Sample test array
            double[] testArray = {10.5, -2.1, 4.8, 10.2, 25.0, 4.95};

            Pair result = findClosestPair(testArray);

            StdOut.println("Array: " + Arrays.toString(testArray));
            StdOut.printf("Closest Pair: %.2f and %.2f\n", result.val1, result.val2);
            StdOut.printf("Minimum Absolute Difference: %.2f\n", result.difference);
        }
    }
}