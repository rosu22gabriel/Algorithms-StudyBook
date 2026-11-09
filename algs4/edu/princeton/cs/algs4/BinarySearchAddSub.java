package edu.princeton.cs.algs4;

import java.util.Arrays;

public class BinarySearchAddSub {
    /**
     * Searches for key in a sorted array using ONLY addition and subtraction (Fibonacci Search)
     * 
     * @param a sorted array of distinct integers
     * @param key target search value
     * @return index of key if present, or -1 if not found
     */

    public static int rank(int key, int[] a) {
        if (a == null || a.length == 0) return -1;
        
        int n = a.length;

        // Initialize Fibonacci numbers: F_k, F_{k-1}, F{k-2}
        int fibM2 = 0; // F_{k-2}
        int fibM1 = 1; // F_{k-1}
        int fibM  = fibM2 + fibM1; // F_k

        // Find the smallest Fibonacci number greater than or equal to N
        while (fibM < n) {
            fibM2 = fibM1;
            fibM1 = fibM;
            fibM = fibM1 + fibM2;
        }

        // Offset marks the eliminated range from the front
        int offset = -1;

        // Search loop using only addition and subtraction
        while (fibM > 1) {
            // Check if fibM2 is a valid index offset + fibM2
            int i = Math.min(offset + fibM2, n - 1);

            if (a[i] < key) {
                // Key is in the right subarray; shift Fibonacci numbers down by 1 step
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1; // Subtraction only
            } else if (a[i] > key) {
                // Key is in the left subarray; shift Fibonacci numbers down by 2 steps
                fibM = fibM2;
                fibM1 = fibM1 - fibM2; // Subtraction only
                fibM2 = fibM - fibM1;  // Subtraction only
            } else {
                return i; // Key found
            }
        }

        // Final boundary check for the last element
        if (fibM1 == 1 && (offset + 1) < n && a[offset + 1] == key) {
            return offset + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        if (args.length > 1) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            Arrays.sort(a);
            int key = Integer.parseInt(args[1]);

            int result = rank(key, a);
            StdOut.println("Index of key " + key + ": " + result);
        } else {
            // Demonstration with sample array
            int[] testArray = {4, 7, 11, 15, 22, 34, 45, 67, 89, 90, 99};
            int key = 34;

            int index = rank(key, testArray);

            StdOut.println("Sorted Array: " + Arrays.toString(testArray));
            StdOut.println("Search Key: " + key);
            StdOut.println("Found Index: " + index + " (value at index: " + (index != -1 ? testArray[index] : "N/A") + ")");
        }
    }

}
