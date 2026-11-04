package edu.princeton.cs.algs4;

import java.util.Arrays;

public class LocalMinimumArray {
    /**
     * Finds the index of a local minimum in an array of distinct integers in O(log N) time.
     * 
     * @param a array of distinct integers
     * @return index of a local minimum, or -1 if array is empty
     */
    public static int findLocalMinimum(int[] a) {
        if (a == null || a.length == 0) return -1;
        if (a.length == 1) return 0;

        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            boolean leftSmaller = (mid > 0) && (a[mid - 1] < a[mid]);
            boolean rightSmaller = (mid < a.length - 1) && (a[mid + 1] < a[mid]);

            if (!leftSmaller && !rightSmaller) {
                // a[mid] is smaller than both adjacent neighbors
                return mid;
            } else if (leftSmaller) {
                // Search left half
                hi = mid - 1;
            } else {
                // Search right half
                lo = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            int index = findLocalMinimum(a);
            StdOut.println("Local minimum Index: " + index);
        } else {
            // Demonstration with sample array
            int[] testArray = {9, 6, 3, 14, 5, 7, 4};

            int index = findLocalMinimum(testArray);

            StdOut.println("Array: " + Arrays.toString(testArray));
            StdOut.println("Local Minimum Index: " + index);
            StdOut.println("Local Minimum Value: " + testArray[index]);
        }
    }
}
