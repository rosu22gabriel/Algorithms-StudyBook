package edu.princeton.cs.algs4;

import java.util.Arrays;

public class BinarySearchSmallestIndex {
    /**
     * Searches for key in a sorted array and returns the smallest index
     * where key appears.
     * 
     * @param key the target search element
     * @param a the sorted array
     * @return the smallest index containing key, or -1 if not found
     */
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int result = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                // Key found; record index and keep searching left for smaller index
                result = mid;
                hi = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            Arrays.sort(a); 

            // Test with a sample key
            int key = 3;
            int smallestIndex = rank(key, a);
            StdOut.println("Smallest index of key " + key + ": " + smallestIndex);
        } else {
            // Demonstration with array containing duplicate values
            int[] testArray = {1, 2, 2, 2, 3, 3, 4, 5, 5, 5, 5, 6};
            int key = 5;

            int index = rank(key, testArray);

            StdOut.println("Array: " + Arrays.toString(testArray));
            StdOut.println("Key: " + key);
            StdOut.println("Smallest Index: " + index + " (value at index: " + testArray[index] + ")");
        }
    }
}