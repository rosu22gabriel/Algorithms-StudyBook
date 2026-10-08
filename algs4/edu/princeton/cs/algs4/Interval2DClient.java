package edu.princeton.cs.algs4;

public class Interval2DClient {

    // Helper method to check if Interval1D 'outer' completely contains Interval1D 'inner'
    private static boolean contains1D(Interval1D outer, Interval1D inner) {
        return outer.min() <= inner.min() && outer.max() >= inner.max();
    }

    // Helper method to check if Interval2D 'outer' completely contains Interval2D 'inner'
    private static boolean contains2D(Interval1D outerX, Interval1D outerY, Interval1D innerX, Interval1D innerY) {
        return contains1D(outerX, innerX) && contains1D(outerY, innerY);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Usage: java Interval2DClient <N> <min> <max>");
            return;
        }
        int N = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);

        Interval1D[] xIntervals = new Interval1D[N];
        Interval1D[] yIntervals = new Interval1D[N];
        Interval2D[] boxes = new Interval2D[N];

        // Configure StdDraw canvas
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0.0, 1.0);
        StdDraw.setYscale(0.0, 1.0);
        StdDraw.setPenRadius(0.002);

        // Generate N random 2D intervals bounded within the unit square [0, 1] x [0, 1]
        for (int i = 0; i < N; i++) {
            double w = StdRandom.uniformDouble(min, max);
            double h = StdRandom.uniformDouble(min, max);
            
            // Ensure coordinates fit inside [0.0, 1.0]
            double x1 = StdRandom.uniformDouble(0.0, 1.0 - w);
            double x2 = x1 + w;
            double y1 = StdRandom.uniformDouble(0.0, 1.0 - h);
            double y2 = y1 + h;

            xIntervals[i] = new Interval1D(x1, x2);
            yIntervals[i] = new Interval1D(y1, y2);
            boxes[i] = new Interval2D(xIntervals[i], yIntervals[i]);

            // Draw interval on standard canvas
            boxes[i].draw();
        }

        // Count intersections and containments for all distinct pairs (i < j)
        int intersectCount = 0;
        int containCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // Check 2D intersection
                if (boxes[i].intersects(boxes[j])) {
                    intersectCount++;
                }

                // Check 2D containment (box i contains box j, or box j contains box i)
                if (contains2D(xIntervals[i], yIntervals[i], xIntervals[j], yIntervals[j]) ||
                    contains2D(xIntervals[j], yIntervals[j], xIntervals[i], yIntervals[i])) {
                        containCount++;    
                }
            }
        }

        // Output results
        StdOut.printf("Generated %d random 2D intervals (width/height in [%.2f, %.2f]:\n", N, min, max);
        StdOut.printf("Number of intersecting pairs: %d\n", intersectCount);
        StdOut.printf("NUmber of pairs with containment: %d\n", containCount);
    }
}
