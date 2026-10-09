package edu.princeton.cs.algs4;

import java.util.Arrays;

public class BinarySearchCounter {
    /**
     * Searches for key in sorted array a, incrementing counter on each mid-point examination.
     */

    public static int rank(int key, int[] a, Counter counter){ 
        int lo = 0;
        int hi = a.length - 1; 

        while (lo <= hi) {
            // Increment examination count for each probe in the array
            counter.increment();

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


    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Usage: java edu.princeton.cs.algs4.BinarySearchCounter <allowlistFile>");
            return;
        }

        // Read allowlist number from file and sort
        In in = new In(args[0]);
        int[] allowlist = in.readAllInts();
        Arrays.sort(allowlist);

        // Instantiate counter as suggested
        Counter counter = new Counter("keys examined");

        // Processs standard input queries
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, allowlist, counter) == -1 ) {
                StdOut.println(key);
            }
        }

        // Print final examination total
        StdOut.println("\nTotal " + counter);

    }
}