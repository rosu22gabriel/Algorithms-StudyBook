package edu.princeton.cs.algs4;

public class StackPermutationChecker {
    /**
     * Determines wether a sequence can be produced  by pushing integers 0..N-1
     * in order onto a stack with intermixed pop operations.
     */
    public static boolean isValidPermutation(int[] targetSequence) {
        Stack<Integer> stack = new Stack<Integer>();
        int currentPush = 0;

        for (int val : targetSequence) {
            // Push values in order until the target element is reached
            while (currentPush <= val) {
                stack.push(currentPush++);
            }

            // Top of stack must match the required output token
            if (!stack.isEmpty() && stack.peek() == val) {
                stack.pop();
            } else {
                return false; // Impossible sequence
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[][] sequences = {
            {4, 3, 2, 1, 0, 9, 8, 7, 6, 5}, // a
            {4, 6, 8, 7, 5, 3, 2, 9, 0, 1}, // b
            {2, 5, 6, 7, 4, 8, 9, 3, 1, 0}, // c
            {4, 3, 2, 1, 0, 5, 6, 7, 8, 9}, // d
            {1, 2, 3, 4, 5, 6, 9, 8, 7, 0}, // e
            {0, 4, 6, 5, 3, 8, 1, 7, 2, 9}, // f
            {1, 4, 7, 9, 8, 6, 5, 3, 0, 2}, // g
            {2, 1, 4, 3, 6, 5, 8, 7, 9, 0}, // h
        };

        char label = 'a';
        for (int[] seq : sequences) {
            boolean valid = isValidPermutation(seq);
            StdOut.printf("Sequence (%c0: %-23s [ %s ] \n",
                label++,
                valid ? "COULD OCCUR" : "COULD NOT OCCUR",
                arrayToString(seq));
        }
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int val : arr) sb.append(val).append(" ");
        return sb.toString().trim();
    }
}