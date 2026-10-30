package edu.princeton.cs.algs4;

public class ThreeSumSafe {
    /**
     * Counts the number of triples in array that sum to exactly 0,
     * using long precision arithmetic to prevent integer overflow.
     * 
     * @param a the array of integers
     * @return the number of triples that sum to 0
     */
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Cast to long before addition to prevent 32-bit integer overflow
                    if ((long) a[i] + (long) a[j] + (long) a[k] == 0L) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Prints all triples in an array that sum to 0.
     * @param a the array of integers
    */
    public static void printAll(int[] a) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((long) a[i] + (long) a[j] + (long) a[k] == 0L) { 
                        StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int[] a = in.readAllInts();
            StdOut.println(count(a));
        } else {
            // Demonstration with values that would overflow standard 32-bit int addition
            int[] testArray = {
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                2 // (MAX_VALUE + MAX_VALUE + 2) would overflow without casting
            };

            StdOut.println("Test array count: " + count(testArray));
        }
    }
}