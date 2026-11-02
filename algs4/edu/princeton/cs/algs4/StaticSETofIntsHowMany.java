package edu.princeton.cs.algs4;
import java.util.Arrays;

public class StaticSETofIntsHowMany {
    private final int[] a;

    /**
     * Initializes the set with the given array of integers.
     * 
     * @param keys the array of integers
     */
    public StaticSETofIntsHowMany(int[] keys) {
        // Defensive copy and sort
        a = new int[keys.length];
        System.arraycopy(keys, 0, a, 0, keys.length);
        Arrays.sort(a);
    }

    /**
     * Returns true if the key is in the set.
     */
    public boolean contains(int key) {
        return rank(key) != -1;
    }

    /**
     * Returns the smallest index of key in the array, or -1 if not found.
     */
    public int rank(int key) {
        return firstIndex(key);
    }

    /**
     * Returns the total count of occurrences of key in O(log N) time.
     * 
     * @param key target search element
     * @return number of occurrences of key
     */
    public int howMany(int key) {
        int first = firstIndex(key);
        if (first == -1) {
            return 0; // Key not found
        }
        int last = lastIndex(key);
        return last - first + 1;
    }

    /**
     * Finds the first (smallest) index of key in O(log N) time.
     */
    private int firstIndex(int key) {
        int lo = 0;
        int hi = a.length -1;
        int result = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                result = mid;   // Key found; record index
                hi = mid - 1;   // Keep searching to the left
            }
        }
        return result;
    }


    /**
     * Finds the last (largest) index of key in O(log N) time. 
     */
    private int lastIndex(int key) {
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
                result = mid;   // Key found; record index
                lo = mid + 1;   // KEep searching to the right
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int[] whiteList = in.readAllInts();
            StaticSETofIntsHowMany set = new StaticSETofIntsHowMany(whiteList);

            int key = 3;
            StdOut.println("Occurences of key " + key + ": " + set.howMany(key));
        } else {
            // Demonstration with array containing duplicate values
            int[] testArray = {1, 2, 2, 2, 3, 3, 4, 5, 5, 5, 5, 6};
            StaticSETofIntsHowMany set = new StaticSETofIntsHowMany(testArray);

            int key = 5;
            int count = set.howMany(key);

            StdOut.println("Array: " + Arrays.toString(testArray));
            StdOut.println("Key: " + key);
            StdOut.println("Number of occurences (howMany): " + count);
        }
    }
}