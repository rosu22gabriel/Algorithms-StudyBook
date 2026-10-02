public class NaturalLogarithm {
    public static double lnFactorial(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N must be non-negative");
        }
        if (N <= 1) {
            return 0.0;
        }
        return Math.log(N) + lnFactorial(N - 1);
    }
    public static void main(String[] args) {
        
    }
}