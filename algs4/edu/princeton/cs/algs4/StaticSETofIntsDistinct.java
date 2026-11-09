package edu.princeton.cs.algs4;

import java.util.Arrays;

public class StaticSETofIntsDistinct {

    private final int[] a;

    /**
     * Constructs a set of distinct integers from the input array.
     * Preprocesses the input in O(N log N) time to remove duplicates.
     *
     * @param keys input array of integers
     */
    public StaticSETofIntsDistinct(int[] keys) {
        if (keys == null || keys.length == 0) {
            a = new int[0];
            return;
        }

        // 1. Sort the input array
        int[] temp = keys.clone();
        Arrays.sort(temp);

        // 2. Count distinct elements (R)
        int r = 1;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] != temp[i - 1]) {
                r++;
            }
        }

        // 3. Populate array with only distinct elements
        a = new int[r];
        a[0] = temp[0];
        int index = 1;
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] != temp[i - 1]) {
                a[index++] = temp[i];
            }
        }
    }

    /**
     * Checks whether key is present in the set in ~ lg R time.
     *
     * @param key target search value
     * @return true if key is in the set, false otherwise
     */
    public boolean contains(int key) {
        return rank(key) != -1;
    }

    /**
     * Binary search on the distinct array of size R.
     * Guaranteed running time: ~ lg R comparisons.
     */
    public int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;

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
     * Returns the number of distinct elements R.
     */
    public int size() {
        return a.length;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int[] whiteList = in.readAllInts();
            StaticSETofIntsDistinct set = new StaticSETofIntsDistinct(whiteList);

            int key = 3;
            StdOut.println("Set size (R): " + set.size());
            StdOut.println("Contains " + key + ": " + set.contains(key));
        } else {
            // Demonstration with array containing heavy duplicates (N = 10, R = 3)
            int[] input = {5, 2, 5, 5, 2, 8, 8, 5, 2, 8};

            StaticSETofIntsDistinct set = new StaticSETofIntsDistinct(input);

            StdOut.println("Original array length (N) : " + input.length);
            StdOut.println("Distinct elements count (R): " + set.size());
            StdOut.println("Distinct array elements   : " + Arrays.toString(set.a));
            StdOut.println("contains(5) -> " + set.contains(5));
            StdOut.println("contains(4) -> " + set.contains(4));
        }
    }
}