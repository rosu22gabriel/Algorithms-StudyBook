public class BinarySearch {
    // clasa nu trebuie instantiata
    private BinarySearch() {  }

    /**
     * Intoarce indexul cheii specificate in array-ul specificat
     * 
     * @param a este array-ul de intregi, care trebuie sortat in ordine crescatoare
     * @param key cheia de cautare
     * @return indexul cheii din array {@code a} daca e prezent; {@code -1} altfel
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // cheia este in a[lo..hi] sa nu este prezenta.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }


    /**
     * 
     * @param key the search key
     * @param a array-ul de intregi, care trebuie sortat in ordine crescatoare
     * @return indexul cheii din array {@code a} daca e prezent; {@code -1} altfel
     * @deprecated inlocuit de {@link #indexOf(int[], int)}.
     */
    @Deprecated
    public static int rank(int key, int[] a)  {
        return indexOf(a, key);
    }

    public static void main(String[] args) {
        // citeste intregii dintr-un fisier
        In in = new In(args[0]);
        int[] allowlist = in.readAllInts();

        // sorteaza array-ul
        Arrays.sort(allowlist);

        // citeste cheia ca intreg din standard input; printeaza daca nu e in allowlist
        while(!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(allowlist, key) == -1)
                StdOut.println(key);
        }
    }
}
