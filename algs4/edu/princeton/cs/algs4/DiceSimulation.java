import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class DiceSimulation {
    // Computes exact probability distribution
    public static double[] getExactDist() {
        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 0; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                dist[i + j] += 1.0;
            }
        }
        for (int k = 2; k <= 2 * SIDES; k++) {
            dist[k] /= 36.0;
        }
        return dist;
    }

    // Computes empirical probability distribution for N trials
    public static double[] getEmpiricalDist(long N) {
        int SIDES = 6;
        double[] empirical = new double[2 * SIDES + 1];
        for (long i = 0; i < N; i++) {
            int die1 = StdRandom.uniformInt(1, 7); // Generates random int in range [1, 6]
            int die2 = StdRandom.uniformInt(1, 7);
            empirical[die1 + die2]++;
        }
        for (int k = 2; k <= 2 * SIDES; k++) {
            empirical[k] /= N;
        }
        return empirical;
    }

    // Checks if empirical probabilities match exact probabilities to 3 decimal places
    public static boolean matchesToThreeDecimals(double[] exact, double[] empirical) {
        for (int k = 2; k < exact.length; k++) {
            if (Math.abs(exact[k] - empirical[k]) >= 0.0005) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        double[] exact = getExactDist();
        StdOut.println("Exact Probabilities:");
        for (int k = 2; k <= 12; k++) {
            StdOut.printf("Sum %2d: %.4f\n", k, exact[k]);
        }
        StdOut.println();


        // Incrementally scale N to find the threshold
        long N = 1000;
        while (true) {
            double[] empirical = getEmpiricalDist(N);
            if (matchesToThreeDecimals(exact, empirical)) {
                StdOut.printf("Empirical results match exact results to 3 decimal places at N = %,d\n", N);
                StdOut.println("\nComparison:");
                StdOut.printf("%-5s | %-8s | %-8s\n", "Sum", "Exact", "Empirical");
                StdOut.println("----------------------");
                for (int k = 2; k <= 12; k++) {
                    StdOut.printf("%-5d | %-8.3f | %-8.3f\n", k, exact[k], empirical[k]);
                }
                break;
            }
            N *= 2;
        }
    }
}
