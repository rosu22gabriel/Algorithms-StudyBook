public class BruteForceSearch {
    /**
     * Sequential/lnear search algorithm as described on page 48.
     * Iterates through array 'a' element-by-element to find 'key'.
     */
    public static int rank(int key, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i; // Found key at index i
        }
        return -1; // Key not found
    }

    public static void main(String[] args) {
        // Read the whitelist from the file provided as command-line argument
        int[] whitelist = In.readInts(args[0]);

        // Start timer 
        Stopwatch timer = new Stopwatch();
        int count = 0;

        // Process keys from standard input (e.g., largeT.txt)
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1) {
                count++;
            }
        }

        double elapsedTime = timer.elapsedTime();
        StdOut.printf("Missing keys count: %d\n", count);
        StdOut.printf("Brute-Force Search Time: %.2f seconds\n", elapsedTime);
    }
}