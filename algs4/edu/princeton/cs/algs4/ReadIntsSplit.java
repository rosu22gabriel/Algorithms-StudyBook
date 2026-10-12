package edu.princeton.cs.algs4;


public class ReadIntsSplit {
    /**
     * Reads all integers from a file specified by name using String.split().
     */
    public static int[] readInts(String name) {
        In in = new In(name);
        String input = in.readAll();

        // Handle null, empty, or whitespace-only inputs safely
        if (input == null || input.trim().isEmpty()) {
            return new int[0];
        }

        // Trim leading/trailing spaces, then split by one or more whitespace characters (\s+)
        String[] words = input.trim().split("\\s+");
        int[] ints = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            ints[i] = Integer.parseInt(words[i]);
        }

        return ints;
    }

    public static void main(String[] args) {
        // Create a temporary test file using algs4 Out class
        String testFilename = "test_file.txt";
        Out out = new Out(testFilename);
        out.println("10 20 30");
        out.println(" 40 \t50");
        out.println("60");
        out.close();


        // Test readInts method
        int[] numbers = readInts(testFilename);

        // Display results
        StdOut.println("Successfully read " + numbers.length + " integers:");
        for (int i = 0; i < numbers.length; i++) {
            StdOut.println("ints[" + i + "] = " + numbers[i]);
        }
    }
}