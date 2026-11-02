package edu.princeton.cs.algs4;

public class PrintIntersection {
    /**
     * Prints all common elements in two sorted arrays in O(N) linear time.
     * 
     * @param a first sorted array
     * @param b second sorted array
     */
    public static void printCommonElements(int[] a, int[] b) {
        if (a == null || b == null) return;

        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                StdOut.print(a[i] + " ");
                int matchedVal = a[i];

                // Skip duplicates in array a
                while (i < a.length && a[i] == matchedVal) {
                    i++;
                }
                // Skip duplicates in array b
                while (j < b.length && b[j] == matchedVal) {
                    j++;
                }
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            In in1 = new In(args[0]);
            In in2 = new In(args[1]);
            int[] a = in1.readAllInts();
            int[] b = in2.readAllInts();

            StdOut.print("Common elements: ");
            printCommonElements(a, b);
        } else {
            // Demonstration with two sample sorted arrays
            int[] a = {1, 2, 4, 5, 8, 8, 10};
            int[] b = {2, 3, 5, 6, 8, 12};

            StdOut.print("Array A: ");
            for (int val : a) StdOut.print(val + " ");
            StdOut.println();

            StdOut.print("Array B: ");
            for (int val : b) StdOut.print(val + " ");
            StdOut.println();

            StdOut.print("Common elements: ");
            printCommonElements(a, b);
        }
    }
}