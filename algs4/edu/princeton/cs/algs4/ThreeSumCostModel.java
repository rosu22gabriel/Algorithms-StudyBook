package edu.princeton.cs.algs4;

public class ThreeSumCostModel {
    public static class CostResult {
        long additions = 0;
        long comparisons = 0;
        int zeroSumCount = 0;
    }

    /**
     * Counts arithmetic operations and comparisons involving input elements in ThreeSum.
     */
    public static CostResult analyzeCost(int[] a) {
        CostResult result = new CostResult();
        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // 2 additions involving input elements
                    long sum = (long) a[i] + (long) a[j] + (long) a[k];
                    result.additions += 2;

                    // 1. comparison against 0
                    result.comparisons++;
                    if (sum == 0L) {
                        result.zeroSumCount++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nValues = {10, 50, 100, 200, 500};
        StdOut.printf("%-8s %15s %-15s %-15s %-15s\n", 
            "N", "Additions", "Theory Add.", "Comparisons", "Theory Comp."
        );
        StdOut.println("-------------------------------------------------------------");

        for (int n : nValues) {
            int[] dummyArray = new int[n];
            CostResult res = analyzeCost(dummyArray);

            long theoreticalAdditions = (long) n * (n - 1) * (n - 2) / 3;
            long theoreticalComparisons = (long) n * (n - 1) * (n - 2) / 6;

            StdOut.printf("%-8d %015d %-15d %-15d %-15d\n", 
                n, res.additions, theoreticalAdditions, res.comparisons, theoreticalComparisons
            );
        }
    }
}