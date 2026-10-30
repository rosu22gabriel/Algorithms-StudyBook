package edu.princeton.cs.algs4;

public class TwoSumTable {
    /**
     * Counts the number of pairs that sum to 0 in array a.
     * 
     * @param a input array
     * @return count of pairs summing to 0
     */
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((long) a[i] + (long) a[j] == 0L) {
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
            StdOut.println("Count: " + count(a));
        } else {
            // Demonstration with a sample array
            int[] test = {-2, -1, 0, 1, 2};
            StdOut.println("Sample Count: " + count(test));
        }
    }
}