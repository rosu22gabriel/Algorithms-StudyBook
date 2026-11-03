package edu.princeton.cs.algs4; 
import java.util.Arrays;

public class TwoSumThreeSumFaster {
    /**
     * Finds pairs that sum to 0 in a sorted array in O(N) time.
     * 
     * @param a sorted array of integers
     * @return total count of pairs summing to 0
     */
    public static int twoSumFaster(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int count = 0;

        while (lo < hi) {
            long sum = (long) a[lo] + (long) a[hi];
            if (sum == 0) {
                // Handle duplicate elements if necessary
                if (a[lo] == a[hi]) {
                    int n = hi - lo + 1;
                    count += n * (n - 1) / 2;
                    break;
                }
                int leftCount = 1;
                int rightCount = 1;
                while (lo + 1 < hi && a[lo] == a[lo + 1]) {
                    leftCount++;
                    lo++;
                }
                while (hi - 1 > lo && a[hi] == a[hi - 1]) {
                    rightCount++;
                    hi--;
                }
                count += leftCount * rightCount;
                lo++;
                hi--;
            } else if (sum < 0) {
                lo++;
            } else {
                hi--;
            }
        }
        return count; 
    }

    /**
     * Finds triples that sum to 0 in O(N^2) time.
     * 
     * @param a input array of integers
     * @return total count of triples summing to 0
     */
    public static int threeSumFaster(int[] a) {
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        int n = sorted.length;
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            int lo = i + 1;
            int hi = n - 1;

            while (lo < hi) {
                long sum = (long) sorted[i] + (long) sorted[lo] + (long) sorted[hi];
                if (sum == 0) {
                    count++;
                    lo++;
                    hi--;
                } else if (sum < 0) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();

            StdOut.println("ThreeSumFaster count: " + threeSumFaster(a));
        } else {
            // Demonstration with sample array
            int[] testArray = {-4, -2, -1, 0, 1, 2, 3, 5};

            int[] sortedTest = testArray.clone();
            Arrays.sort(sortedTest);

            int count2Sum = twoSumFaster(sortedTest);
            int count3Sum = threeSumFaster(testArray);

            StdOut.println("Array: " + Arrays.toString(testArray));
            StdOut.println("TwoSumFaster (O(N) on sorted) count: " + count2Sum);
            StdOut.println("ThreeSumFaster (O(N^2)) count      : " + count3Sum);
        }
    }
}