import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;



public class RandomMatches {
    /**
     * Generates an array of N random 6-digit integers in range[100000, 999999].
     */
    private static int[] generateRandomArray(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            // StdRandom.uniformInt(a, b) produces random int in range [a, b - 1]
            a[i] = StdRandom.uniformInt(100000, 1000000);
        }
        return a;
    }

    /**
     * Runs a single experiment for array size N;
     * Generates two arrays of size N, sorts the second, and uses
     * BinarySearch to count how many elements of a1 exist in a2.
     */
    private static int countMatches(int N) {
        int[] a1 = generateRandomArray(N);
        int[] a2 = generateRandomArray(N);

        // Sort a2 to enable binary search
        Arrays.sort(a2);

        int matches = 0; 
        for (int i = 0; i < N; i++) {
            if (BinarySearch.rank(a1[i], a2) != -1) {
                matches++;
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        int[] sizes = {1000, 10000, 100000, 1000000};

        StdOut.printf("%-10s | %-12s | %-18s\n", "N", "Exponent", "Avg Matches (T=" + T + ")");
        StdOut.println("--------------------------------------------------");

        for (int N : sizes) {
            long totalMatches = 0;
            for (int t = 0; t < T; t++) {
                totalMatches += countMatches(N);
            }
            double avgMatches = (double) totalMatches / T;
            StdOut.printf("%-10d | 10^%-9d | %-18.2f\n", N, (int) Math.log10(N), avgMatches);
        }
    }
}