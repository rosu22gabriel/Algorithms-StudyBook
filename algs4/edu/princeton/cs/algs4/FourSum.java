package edu.princeton.cs.algs4;

import java.util.Arrays;

public class FourSum {
    /**
     * Counts quadruples that sum to zero in O(N^3 log N) time using binary search.
     * 
     * @param a input array of integers
     * @return count of 4-tuples summing to 0
     */
    public static int count(int[] a) {
        int n = a.length;
        int[] sorted = a.clone();
        Arrays.sort(sorted);

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    long target = -((long) sorted[i] + (long) sorted[j] + (long) sorted[k]);

                    // Search for target in the sub-array following index k
                    int l = BinarySearch.rank((int) target, sorted);
                    if (l > k) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Counts quadruples that sum to zero in O(N^4) brute-force time.
     */
    public static int countBruteForce(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if ((long) a[i] + (long) a[j] + (long) a[k] + (long) a[l] == 0L) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            StdOut.println("4-Sum count: " + count(a));
        } else {
            // Sample test array
            int[] testArray = {-3, -2, -1, 0, 1, 2, 3, 4};
            int fastCount = count(testArray);
            int bruteCount = countBruteForce(testArray);

            StdOut.println("Array: " + Arrays.toString(testArray));
            StdOut.println("Fast O(N^3 log N) Count: " + fastCount);
            StdOut.println("Brute-force O(N^4) Count: " + bruteCount);
        }
    }
}