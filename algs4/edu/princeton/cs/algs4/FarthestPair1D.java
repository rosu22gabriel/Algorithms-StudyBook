package edu.princeton.cs.algs4;

import java.util.Arrays;

public class FarthestPair1D {
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
     * Finds the two farthest values in an array of double values in O(N) linear time.
     * 
     * @param a input array of double values
     * @return Pair containing the two farthest numbers
     */
    public static Pair findFarthestPair(double[] a) {
        if (a == null || a.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        double min = a[0];
        double max = a[0];

        for (double value : a) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        return new Pair(min, max);
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            double[] a = in.readAllDoubles();
            Pair result = findFarthestPair(a);
            StdOut.printf("Farthest pair: (%.4f, %.4f (difference: %.4f)\n", result.val1, result.val2, result.difference);
        } else {
            // Sample test array
            double[] testArray = {10.5, -2.1, 4.8, 10.2, 25.0, 4.95};

            Pair result = findFarthestPair(testArray);

            StdOut.println("Array: " + Arrays.toString(testArray));
            StdOut.printf("Farthest pair: (%.4f, %.4f) (difference: %.4f)\n", result.val1, result.val2, result.difference);
        }
    }
}
