import edu.princeton.cs.algs4.StdOut;

public class MemoryUsageAnalysis {

    public static void main(String[] args) {
        StdOut.println("=== Memory Usage Table (64-bit Architecture) ===");
        StdOut.printf("%-35s %-20s\n", "Class / Type", "Memory (Bytes)");
        StdOut.println("-------------------------------------------------------");
        StdOut.printf("%-35s %-20s\n", "a. Accumulator", "32 bytes");
        StdOut.printf("%-35s %-20s\n", "b. Transaction (shallow)", "40 bytes");
        StdOut.printf("%-35s %-20s\n", "c. FixedCapacityStackOfStrings", "56 + 8C bytes");
        StdOut.printf("%-35s %-20s\n", "d. Point2D", "32 bytes");
        StdOut.printf("%-35s %-20s\n", "e. Interval1D", "32 bytes");
        StdOut.printf("%-35s %-20s\n", "f. Interval2D", "96 bytes");
        StdOut.printf("%-35s %-20s\n", "g. Double", "24 bytes");
    }
}