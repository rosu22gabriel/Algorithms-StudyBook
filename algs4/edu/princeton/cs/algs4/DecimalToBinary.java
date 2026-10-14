package edu.princeton.cs.algs4;

public class DecimalToBinary {
    /**
     * Computes and prints the binary representation of a positive integer N.
     */
    public static void printBinary(int N) {
        if (N == 0) { 
            StdOut.println(0);
            return;
        }

        Stack<Integer> stack = new Stack<Integer>();
        while (N > 0) {
            stack.push(N % 2);
            N = N / 2;
        }

        for (int d : stack) {
            StdOut.print(d);
        }
        StdOut.println();
    }

    /**
     * Main testing function verifying output for N = 50 and additional integers.
     */
    public static void main(String[] args) {
        int n = 50;
        StdOut.print("Binary representation of " + n + ": ");   
        printBinary(n); // Expected output: 110010

        // Additional test cases
        int[] testCases = {1, 2, 8, 13, 255, 1024};
        StdOut.println("\n Additional Test Client Cases:");
        for (int test: testCases) {
            StdOut.printf("%-5d -> ", test);
            printBinary(test);
        }
    }
}