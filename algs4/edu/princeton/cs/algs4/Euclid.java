import edu.princeton.cs.algs4.StdOut;

public class Euclid {
    /**
     * Recursive implementation of Euclid's algorithm that traces (p, q) arguments.
     */

    public static int gcd(int p, int q) {
        StdOut.println(p + " " + q);
        if (q == 0) { 
            return p;
        }
        return gcd(q, p % q);
    }

    public static void main(String[] args) {
        int p = Integer.parseInt(args[0]);
        int q = Integer.parseInt(args[1]);

        int result = gcd(p, q);
        StdOut.println("GCD: " + result);
    }
}