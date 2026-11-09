package edu.princeton.cs.algs4;

import java.util.Arrays;

public class BitonicSearch {
    /**
     * Finds the index of the maximum element (peak) in a bitonic array in O(log N) time.
     */
    private static int findPeak(int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] < a[mid + 1]) {
                lo = mid + 1; // Peak is to the right
            } else {
                hi = mid;     // Peak is at mid or to the left
            }
        }
        return lo;
    }

    /**
     * Standard binary search on an ascending sorted array segment.
     */
    private static int binarySearchAscending(int[] a, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Binary search on a descending sorted array segment.
     */
    private static int binarySearchDescending(int[] a, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                lo = mid + 1;
            } else if (key > a[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * Searches for key in a bitonic array of O(log N) time.
     * 
     * @param a bitonic array of distinc integers
     * @param key target search value
     * @return index of key if found, or -1 if not found
     */
    public static int contains(int[] a, int key) {
        if (a == null || a.length == 0) return -1;

        int peak = findPeak(a);

        // Search in increasing left half
        int index = binarySearchAscending(a, key, 0, peak);
        if (index != -1) {
            return index;
        }

        // Search in decreasing right half
        return binarySearchDescending(a, key, peak + 1, a.length - 1);
    }

    public static void main(String[] args) {
        if (args.length > 1) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            int key = Integer.parseInt(args[1]);

            int result = contains(a, key);
            StdOut.println("Key " + key + " found at index: " + result);
        } else {
            // Sample test bitonic array (increases then decreases)
            int[] testArray = {1, 3, 7, 12, 19, 15, 10, 4, 2, 0};
            int keyToFind = 10;

            int peakIndex = findPeak(testArray);
            int foundIndex = contains(testArray, keyToFind);

            StdOut.println("Bitonic Array: " + Arrays.toString(testArray));
            StdOut.println("Peak Element: " + testArray[peakIndex] + " at index " + peakIndex);
            StdOut.println("Search Key: " + keyToFind);
            StdOut.println("Result Index: " + foundIndex);
        }
    }
}
