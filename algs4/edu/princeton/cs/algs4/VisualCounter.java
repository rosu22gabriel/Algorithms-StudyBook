package edu.princeton.cs.algs4;

public class VisualCounter {
    private final int maxOps; // Maximum number of operations allowed (N)
    private final int maxVal; // Maximum absolute value for tally (|tally| <= maxVal)
    private int ops;          // Count of operations performed so far
    private int tally;        // Current tally value

    /**
     * Initializes a VisualCounter with operation cap N and maximum absolute bound max.
     */
    public VisualCounter(int N, int max) {
        this.maxOps = N;
        this.maxVal = Math.abs(max);
        this.ops = 0;
        this.tally = 0;

        // Configure StdDraw scale and canvas
        StdDraw.setCanvasSize(800, 500);
        StdDraw.setXscale(0, maxOps);
        StdDraw.setYscale(-maxVal, maxVal);
        StdDraw.setPenRadius(0.005);

        // Draw baseline axis at y = 0
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(0, 0, maxOps, 0);

        // Draw initial point at origin (0, 0)
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(0, 0);
    }

    /**
     * Increments tally by 1 if operation cap and maximum positive bound are not exceeded.
     */
    public void increment() {
        if (ops < maxOps  && tally < maxVal) {
            ops++;
            tally++;
            plotPoint();
        } else {
            StdOut.println("Increment ignored: operational or boundary limit reached.");
        }
    }

    /**
     * Decrements tally by 1 if operation cap and maximum negative bound are not exceeded
     */
    public void decrement() {
        if (ops < maxOps && tally > -maxVal) {
            ops++;
            tally--;
            plotPoint();
        } else {
            StdOut.println("Decrement ignored: operational or boundary limit reached.");
        }
    }

    /**
     * Plots a point representing the counter state at (ops, tally).
     */
    private void plotPoint() {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(ops, tally);
    }

    /**
     * Returns the current tally value.
     */
    public int tally() {
        return tally;
    }

    /**
     * Returns the current operation count.
     */
    public int ops() {
        return ops;
    }

    public static void main(String[] args) {
        int N = 100;
        int max = 20;

        VisualCounter counter = new VisualCounter(N, max);

        // Perform random increments and decrements to simulate random wlak
        for (int i = 0; i < N; i++) {
            if (StdRandom.bernoulli(0.55)) {
                counter.increment();
            } else {
                counter.decrement();
            }
        }

        StdOut.printf("Final Tally: %d\n", counter.tally());
        StdOut.printf("Total Operations: %d\n", counter.ops());
    }
}