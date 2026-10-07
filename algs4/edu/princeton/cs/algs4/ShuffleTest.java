import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShuffleTest {
    /**
     * Shuffles an integer array in place using the standard Knuth/Fisher-Yates
     * shuffle algorithm (Page 32).
     */
    public static void shuffle(int[] a) {
        int M = a.length;
        for (int i = 0; i < M; i++) {
            // Select an integer uniformly in [i, M - 1]
            int r = i + StdRandom.uniformInt(M - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);

        // table[i][j] tracks how many times value i ended up in index j
        int[][] table = new int[M][M];
        int[] a = new int[M];

        for (int sim = 0; sim < N; sim++) {
            // Initialize array: a[i] = i
            for (int i = 0; i < M; i++) {
                a[i] = i;
            }

            // Shuffle array
            shuffle(a);

            // Record final positions: value a[j] landed at index j
            for (int j = 0; j < M; j++) {
                table[a[j]][j]++;
            }
        }

        // Output results
        StdOut.printf("Empirical Shuffle Test (M = %d, N = %d, Expected N/M = %d) :\n\n", M, N, N/M);

        // Header row for columns (positions)
        StdOut.print("Pos:    ");
        for (int i = 0; i < M; i++) {
            StdOut.printf("Val %2: ", i);
            for (int j = 0; j < M; j++) {
                StdOut.printf("%6d ", table[i][j]);
            }
            StdOut.println();
        }
    }
}