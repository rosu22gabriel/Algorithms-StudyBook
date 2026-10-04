public class BetterFibonacci { 
    public static void main(String[] args) {
        int maxN = 100;
        long[] f = new long[maxN];

        f[0] = 0;
        if(maxN > 1) f[1] = 1;

        for (int i = 2; i < maxN; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        for (int N = 0; N < maxN; N++) {
            System.out.println(N + " " + f[N]);
        }
    }

}