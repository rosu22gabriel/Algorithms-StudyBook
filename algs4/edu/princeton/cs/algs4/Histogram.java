import java.awt.Color;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Histogram {
    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Usage: java Histogram <N> <l> <r>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);

        double intervalWidth = (r - l) / N;
        int[] counts = new int[N];

        // 1. Read values from standard input and count frequencies per bin
        while (!StdIn.isEmpty()) {
            double val = StdIn.readDouble();

            // Include values within [l, r]
            if (val >= 1 && val <= r) {
                int bin = (int) ((val - l) / intervalWidth);

                // Handle edge case where val == r
                if (bin == N) {
                    bin = N - 1;
                }

                counts[bin]++;
            }
        }

        // 2. Find maximum count to scale the y-axis
        int maxCount = 0;
        for (int count : counts) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        // 3. Configure StdDraw scales with a slight margin
        double xMargin = (r - l) * 0.05;
        double yMax = (maxCount == 0) ? 1.0 : maxCount * 1.1;

        StdDraw.setCanvasSize(800, 500);
        StdDraw.setXscale(l - xMargin, r + xMargin);
        StdDraw.setYscale(0, yMax);

        // 4. Plot histogram bars
        double barHalfWidth = (intervalWidth / 2.0) * 0.95; // 5% gap between bars for visual distinction

        for (int i = 0; i < N; i++) {
            double xCenter = l + (i + 0.5) * intervalWidth;
            double yCenter = counts[i] / 2.0;
            double halfHeight = counts[i] / 2.0;

            if (counts[i] > 0) {
                // Draw filled bar
                StdDraw.setPenColor(Color.GRAY);
                StdDraw.filledRectangle(xCenter, yCenter, barHalfWidth, halfHeight);
                
                // Draw bar outline
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.rectangle(xCenter, yCenter, barHalfWidth, halfHeight);
            }
        }

        // 5. Draw reference baseline
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(l, 0, r, 0);
    }
}