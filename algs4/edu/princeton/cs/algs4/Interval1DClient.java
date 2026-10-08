import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Interval1DClient {
    public static void main(String[] args){ 
        if (args.length < 1) {
            StdOut.println("Usage: java Interval1DClient <N>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        Interval1D[] intervals = new Interval1D[N];

        // Read N pairs of double values from standard input
        for (int i = 0; i < N; i++) {
            double d1 = StdIn.readDouble();
            double d2 = StdIn.readDouble();

            // Ensure min <= max required by Interval1D constructor
            double min = Math.min(d1, d2);
            double max = Math.max(d1, d2);

            intervals[i] = new Interval1D(min, max);
        }

        // Compare all distinct pairs of intervals (i < j)
        StdOut.println("\nIntersecting Pairs:");
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (intervals[i].intersects(intervals[j])) {
                    StdOut.println(intervals[i] + " intersects " + intervals[j]);
                    count++;
                }
            }
        }

        StdOut.printf("\nTotal intersecting pairs: %d\n", count);
    }
}