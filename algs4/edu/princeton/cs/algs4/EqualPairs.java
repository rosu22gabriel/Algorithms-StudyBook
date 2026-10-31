package edu.princeton.cs.algs4;

import java.util.Arrays;

public class EqualPairs {
    /**
     * Counts the number of equal pairs in linearithmic O(N log N) time.
     * 
     * @param a the input array
     * @return the number of equal pairs
     */
    public static long countEqualPairsFast(int[] a) {
        if (a == null || a.length < 2) return 0;

        // Make a copy to avoid mutating the original input array
        int[] sorted = a.clone();
        Arrays.sort(sorted);

        long totalPairs = 0;
        long runLength = 1;

        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] == sorted[i - 1]) {
                runLength++;
            } else {
                // Add combinations for the completed group: k * (k - 1) / 2
                totalPairs += runLength * (runLength - 1) / 2;
                runLength = 1;
            }
        }
        // Account for the final group at the end of the array
        totalPairs += runLength * (runLength - 1) / 2;

        return totalPairs;
    }

    /**
     * Counts the number of equal pairs in quadratic O(N^2) time (for verification).
     * 
     * @param a the input array
     * @return the number of equal pairs
     */
    public static long countEqualPairsSlow(int[] a) {
        long count = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] == a[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            long pairs = countEqualPairsFast(a);
            StdOut.println("Number of equal pairs: " + pairs);
        } else {
            // Demonstration with sample array containing duplicate values
            int[] testArray = {1, 2, 2, 3, 3, 3, 4, 1};

            long fastCount = countEqualPairsFast(testArray);
            long slowCount = countEqualPairsSlow(testArray);

            StdOut.println("Test Array: " + Arrays.toString(testArray));
            StdOut.println("Fast O(N log N) Count: " + fastCount);
            StdOut.println("Slow O(N^2) Count:" + slowCount);
        }
    }
}