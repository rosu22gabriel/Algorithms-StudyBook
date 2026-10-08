import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ClosestPairPersonal {
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Usage: java ClosestPair <N>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        if (N < 2) {
            StdOut.println("Please provide N>=2 to compute a distance between points.");
            return;
        }

        // Generate N random points in the unit square [0.0, 1.0] x [0.0, 1.0]
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniformDouble(0.0, 1.0);
            double y = StdRandom.uniformDouble(0.0, 1.0);
            points[i] = new Point2D(x, y);
        }

        // Compute N random points in the unit square [0.0, 1.0] x [0.0, 1.0]
        double minDistance = Double.POSITIVE_INFINITY;
        Point2D closestP1 = null;
        Point2D closestP2 = null;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = points[i].distanceTo(points[j]);
                if (dist < minDistance) {
                    minDistance = dist;
                    closestP1 = points[i];
                    closestP2 = points[j];
                }
            }
        }

        // Print results
        StdOut.printf("Number of points(N): %,d\n", N);
        StdOut.printf("Closest pair distance: %.6f\n", minDistance);
        if (closestP1 != null && closestP2 != null) {
            StdOut.printf("Point 1: %s\n", closestP1);
            StdOut.printf("Point 2: %s\n", closestP2);
        }
    }
}