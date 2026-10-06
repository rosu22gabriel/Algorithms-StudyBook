import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Matrix {
    /**
     * Vector dot product: sum(x[i] * y[i])
     */
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Vector lengths must match.");
        }
        double sum = 0.0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i] * y[i];
        }
        return sum;
    }

    /**
     * Matrix-matrix product: C = A * B
     */
    public static double[][] mult(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        int p = b[0].length;

        if (b.length != n) {
            throw new IllegalArgumentException("Matrix dimensions do not match for multiplication");
        }

        double[][] c = new double[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    /**
     * Transpose of matrix A
     */
    public static double[][] transpose(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] t = new double[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[j][i] = a[i][j];
            }
        }
        
        return t;
    }

    /**
     * Matrix-vector product: y = A * x
     */
    public static double[] mult(double[][] a, double[] x) {
        int m = a.length;
        int n = a[0].length;

        if (x.length != n) {
            throw new IllegalArgumentException("Matrix columns must equal vector length.");
        }

        double[] y = new double[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                y[i] += a[i][j] * x[j];
            }
        }
        return y;
    }

    /**
     * Vector-matrix product: x = y * A
     */
    public static double[] mult(double[] y, double[][] a) { 
        int m = a.length;
        int n = a[0].length;

        if (y.length != m) {
            throw new IllegalArgumentException("Vector length must equal matrix rows.");
        }

        double[] x = new double[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                x[j] += y[i] * a[i][j];
            }
        }
        return x;
    }

    // --- Helper printing functions ---
    
    private static void printVector(double[] v) {
        for (double val : v) {
            StdOut.printf("%8.2f ", val);
        }
        StdOut.println();
    }

    private static void printMatrix(double[][] m) {
        for (double[] row : m) {
            printVector(row);
        }
    }

    /**
     * Test Client
     */
    public static void main(String[] args) {
        StdOut.println("---1. Vector Dot Product ---");
        double[] x = {1.0, 2.0, 3.0};
        double[] y = {4.0, 5.0, 6.0};
        StdOut.printf("x . y = %.2f%n%n", dot(x, y));

        StdOut.println("--- 2. Matrix-Matrix Product ---");
        double[][] a = { 
            {1.0, 2.0},
            {3.0, 4.0}
        };
        double[][] b = {
            {5.0, 6.0},
            {7.0, 8.0}
        };
        StdOut.println("A * B =");
        printMatrix(mult(a, b));
        StdOut.println();

        StdOut.println("--- 3. Transpose ---");
        StdOut.println("A^T =");
        printMatrix(transpose(a));
        StdOut.println();

        StdOut.println("--- 4. Matrix-Vector Product ---");
        double[] v1 = {1.0, 2.0};
        StdOut.println("A * v =");
        printVector(mult(a, v1));
        StdOut.println();

        StdOut.println("--- 5. Vector-Matrix Product --- ");
        double[] v2 = {1.0, 2.0};
        StdOut.println("v * A =");
        printVector(mult(v2, a));
    }
}