package edu.princeton.cs.algs4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class DoublingTestPlot { 
    private static class DataPoint {
        final double n;
        final double time;

        DataPoint(double n, double time) {
            this.n = n;
            this.time = time;
        }
    }

    /**
     * Measures running time of ThreeSum.count for a given array size N.
     */
    public static double timeTrial(int n) {
        int max = 100000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(-max, max);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        List<DataPoint> points = new ArrayList<>();

        // Initial settings for double drawing windows
        StdDraw.setCanvasSize(1000, 500);

        // Run trials while generating data points
        for (int n = 250; n <= 4000; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %5.1f\n", n, time);
            points.add(new DataPoint(n, time));

            // Redraw plots with auto-rescaling after every new trial
            drawPlots(points);
        }
    }

    /**
     * Redraws both standard and log-log plots rescaled to fit window dimensions.
     */
    private static void drawPlots(List<DataPoint> points) {
        StdDraw.clear();

        // Find maximum bounds for dynamic axis scaling
        double maxN = points.get(points.size() - 1).n;
        double maxTime = points.get(points.size() - 1).time;

        // Ensure non-zero time for safe logarithmic calculations
        if (maxTime <= 0) maxTime = 0.001;

        // --- Left Viewport: Standard Plot (Linear-Linear) ---
        drawStandardPlot(points, maxN, maxTime);

        // --- Right Viewport: Log-Log Plot ---
        drawLogLogPlot(points, points.get(0).n, maxN, points.get(0).time, maxTime);

        StdDraw.show();
    }

    private static void drawStandardPlot(List<DataPoint> points, double maxN, double maxTime) {
        // Left viewport coordinates: x in [0.05, 0.45], y in [0.15, 0.85]
        double xMin = 0.05, xMax = 0.45;
        double yMin = 0.15, yMax = 0.85;

        // Axes
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.line(xMin, yMin, xMax, yMin);
        StdDraw.line(xMin, yMin, xMin, yMax);

        // Labels
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text((xMin + xMax) / 2, 0.05, "Standard Plot (N vs Time)");
        StdDraw.textLeft(xMin, yMax + 0.05, String.format("Time max: %.2fs", maxTime));

        // Plot points and connected lines
        StdDraw.setPenColor(Color.BLUE);
        for (int i = 0; i < points.size(); i++) {
            DataPoint p = points.get(i);
            double x = xMin + (p.n / maxN) * (xMax - xMin);
            double y = yMin + (p.time / maxTime) * (yMax - yMin);

            StdDraw.filledCircle(x, y, 0.005);

            if (i > 0) {
                DataPoint prev = points.get(i - 1);
                double prevX = xMin + (prev.n / maxN) * (xMax - xMin);
                double prevY = yMin + (prev.time / maxTime) * (yMax - yMin);
                StdDraw.line(prevX, prevY, x, y);
            }
        }
    }

    private static void drawLogLogPlot(List<DataPoint> points, double minN, double maxN, double minTime, double maxTime) {
        // Right viewport coordinates: x in [0.55, 0.95], y in [0.15, 0.85]
        double xMin = 0.55, xMax = 0.95;
        double yMin = 0.15, yMax = 0.85;

        // Logarithmic bounds
        double logMinN = Math.log(minN);
        double logMaxN = Math.log(maxN);

        // Safeguard logarithmic calculation for small/zero values
        double safeMinTime = Math.max(minTime, 0.0001);
        double safeMaxTime = Math.max(maxTime, 0.001);
        double logMinTime = Math.log(safeMinTime);
        double logMaxTime = Math.log(safeMaxTime);

        // Axes
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.line(xMin, yMin, xMax, yMin);
        StdDraw.line(xMin, yMin, xMin, yMax);

        // Labels 
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text((xMin + xMax) / 2, 0.05, "Log-log Plot (lg N vs lg Time)");

        // Plot points and connected lines
        StdDraw.setPenColor(Color.RED);
        for (int i = 0; i < points.size(); i++) {
            DataPoint p = points.get(i);
            
            double logN = Math.log(p.n);
            double logT = Math.log(Math.max(p.time, 0.0001));

            double x = xMin + ((logN - logMinN) / (logMaxN - logMinN == 0 ? 1 : logMaxN - logMinN)) * (xMax - xMin);
            double y = yMin + ((logT - logMinTime) / (logMaxTime - logMinTime == 0 ? 1 : logMaxTime - logMinTime)) * (yMax - yMin);

            StdDraw.filledCircle(x, y, 0.005);

            if (i > 0) {
                DataPoint prev = points.get(i - 1);
                double prevLogN = Math.log(prev.n);
                double prevLogT = Math.log(Math.max(prev.time, 0.0001));

                double prevX = xMin + ((prevLogN - logMinN) / (logMaxN - logMinN == 0 ? 1 : logMaxN - logMinN)) * (xMax - xMin);
                double prevY = yMin + ((prevLogT - logMinTime) / (logMaxTime - logMinTime == 0 ? 1 : logMaxTime - logMinTime)) * (yMax - yMin);

                StdDraw.line(prevX, prevY, x, y);
            }
        }
    }
}