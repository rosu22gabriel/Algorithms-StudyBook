package edu.princeton.cs.algs4;

public class TildeApproximationTest {
    public static void main(String[] args){ 
        long[] nValues = {100, 1000, 10000, 100000, 1000000};

        StdOut.printf("%-10s %-12s %-12s %-12s %-12s %-12s %-12s\n",
                      "N", "a (N)", "b (1)", "c (1)", "d (2N^3)", "e (1)", "f (2)");
        StdOut.println("---------------------------------------------------------------------------------");
        
        for (long n : nValues) {
            double doubleN = (double) n;

            // Ratio of Exact / Approximation (should converge to 1.0)
            double ratioA = (doubleN + 1.0) / doubleN;
            double ratioB = (1.0 + 1.0 / doubleN) / 1.0;
            double ratioC = ((1.0 + 1.0 / doubleN) * (1.0 + 2.0 / doubleN)) / 1.0;
            double ratioD = (2.0 * Math.pow(doubleN, 3) + 15.0 * Math.pow(doubleN, 2) + doubleN) / (2.0 * Math.pow(doubleN, 3));
            double ratioE = (Math.log(2 * doubleN) / Math.log(doubleN)) / 1.0;
            double ratioF = (Math.log(Math.pow(doubleN, 2) + 1) / Math.log(doubleN)) / 2.0;

            StdOut.printf("%-10d %-12.6f %-12.6f %-12.6f %-12.6f %-12.6f %-12.6f\n",
                n, ratioA, ratioB, ratioC, ratioD, ratioE, ratioF);
        }
    }
}