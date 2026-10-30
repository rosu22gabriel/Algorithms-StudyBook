package edu.princeton.cs.algs4;

public class TripleCount {
    /**
     * Calculates the number of triples from N items using mathematical combination.
     * 
     * @param n total number of items
     * @return total count of triples
     */
    public static long countTriplesFormula(long n) {
        if (n < 3) return 0;
        return n * (n - 1) * (n - 2) / 6;
    }

    /**
     * Empirical count using three nested loops to verify the theoretical result.
     * 
     * @param n total number of items
     * @return total count of triples
     */
    public static long countTriplesEmpirical(int n) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] testCases = {3, 4, 5, 10, 100};

        StdOut.printf("%-10s %-20s %-20s\n", "N", "Formula Result", "Empirical Count");
        StdOut.println("----------------------------------------------");

        for (int n : testCases) {
            long formulaResult = countTriplesEmpirical(n);
            long empiricalResult = countTriplesEmpirical(n);
            StdOut.printf("%-10d %-20d %-20d\n", n, formulaResult, empiricalResult);
        }
    }
}