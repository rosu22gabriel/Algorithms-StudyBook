import edu.princeton.cs.algs4.StdOut;

public class FractionBinarySearch {

    public static class Fraction {
        public final long p;
        public final long q;

        public Fraction(long p, long q) {
            long g = gcd(p, q);
            this.p = p / g;
            this.q = q / g;
        }

        private static long gcd(long a, long b) {
            while (b != 0) {
                long temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }

        @Override
        public String toString() {
            return p + "/" + q;
        }
    }

    /**
     * Simulated Black-Box environment holding the secret fraction.
     */
    public static class SecretFraction {
        private final double targetValue;

        public SecretFraction(long p, long q) {
            this.targetValue = (double) p / q;
        }

        public boolean isLessThan(double x) {
            return targetValue < x;
        }
    }

    /**
     * Finds the unknown rational number p/q (0 < p < q < N) using a logarithmic 
     * number of queries (~ 2 lg N).
     *
     * @param secret black-box query oracle
     * @param N upper bound on denominator q
     * @return exact Fraction p/q
     */
    public static Fraction findFraction(SecretFraction secret, long N) {
        double lo = 0.0;
        double hi = 1.0;
        double threshold = 1.0 / (N * N);

        // Perform ~ 2 lg N queries
        while (hi - lo >= threshold) {
            double mid = lo + (hi - lo) / 2.0;
            if (secret.isLessThan(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        double approx = lo + (hi - lo) / 2.0;

        // Reconstruct exact fraction p/q using Stern-Brocot / Farey Mediants
        long pL = 0, qL = 1; // Left boundary 0/1
        long pR = 1, qR = 1; // Right boundary 1/1

        while (true) {
            long pM = pL + pR; // Mediant numerator
            long qM = qL + qR; // Mediant denominator

            if (qM >= N) {
                // Return closest valid fraction with denominator < N
                double valL = (double) pL / qL;
                double valR = (double) pR / qR;
                if (Math.abs(valL - approx) < Math.abs(valR - approx)) {
                    return new Fraction(pL, qL);
                } else {
                    return new Fraction(pR, qR);
                }
            }

            double valM = (double) pM / qM;
            if (valM == approx) {
                return new Fraction(pM, qM);
            } else if (valM < approx) {
                pL = pM;
                qL = qM;
            } else {
                pR = pM;
                qR = qM;
            }
        }
    }

    public static void main(String[] args) {
        long N = 1000;
        long secretP = 355;
        long secretQ = 713; // 0 < 355 < 713 < 1000

        SecretFraction secret = new SecretFraction(secretP, secretQ);
        Fraction result = findFraction(secret, N);

        StdOut.println("Target Secret Fraction : " + secretP + "/" + secretQ);
        StdOut.println("Discovered Fraction    : " + result);
        StdOut.println("Decimal Value          : " + ((double) secretP / secretQ));
    }
}