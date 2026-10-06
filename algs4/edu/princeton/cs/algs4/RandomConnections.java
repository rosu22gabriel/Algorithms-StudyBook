import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class RandomConnections {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java RandomConnections <N> <p>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);

        // Circle coordinates in unit square [0, 1] x [0, 1]
        double centerX = 0.5;
        double centerY = 0.5;
        double radius = 0.4; // Leaves a 0.1 margin for point radius

        // Calculate coordinates for N equally spaced points
        double[] x = new double[N];
        double[] y = new double[N];

        for (int i = 0; i < N; i++) {
            double angle = 2 * Math.PI * i / N;
            x[i] = centerX + radius * Math.cos(angle);
            y[i] = centerY + radius * Math.sin(angle);
        }

        // Setup canvas
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        // 1. Draw connecting lines first (so they render underneath the dots)
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.setPenRadius(0.002); // Thin gray lines for clarity

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(x[i], y[i], x[j], y[j]);
                }
            }
        }

        // 2. Draw dots on top of the line endpoints
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.05); // Requestesd dot size of 0.05

        for (int i = 0; i < N; i++) {
            StdDraw.point(x[i], y[i]);
        }
    }
}