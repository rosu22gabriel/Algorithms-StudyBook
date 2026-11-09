package edu.princeton.cs.algs4;

public class EggDropBuilding {

    /**
     * Simulated building environment tracking egg drop attempts and breaks.
     */
    public static class Building {
        private final int n;
        private final int f;
        private int throwsCount;
        private int brokenCount;

        public Building(int n, int f) {
            this.n = n;
            this.f = f;
            this.throwsCount = 0;
            this.brokenCount = 0;
        }

        /**
         * Simulates dropping an egg from the specified floor.
         *
         * @param floor floor number
         * @return true if egg breaks, false otherwise
         */
        public boolean drop(int floor) {
            throwsCount++;
            if (floor >= f) {
                brokenCount++;
                return true;
            }
            return false;
        }

        public int getThrowsCount() {
            return throwsCount;
        }

        public int getBrokenCount() {
            return brokenCount;
        }

        public void resetStats() {
            throwsCount = 0;
            brokenCount = 0;
        }
    }

    /**
     * Strategy 1: Standard Binary Search (~ lg N throws, ~ lg N broken eggs).
     */
    public static int findFloorBinarySearch(Building b, int n) {
        int lo = 1;
        int hi = n;
        int resultF = n + 1; // Default if egg never breaks

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (b.drop(mid)) {
                resultF = mid;
                hi = mid - 1; // Try lower floors
            } else {
                lo = mid + 1; // Try higher floors
            }
        }
        return resultF;
    }

    /**
     * Strategy 2: Exponential Search (~ 2 lg F throws, ~ lg F broken eggs).
     */
    public static int findFloorExponentialSearch(Building b, int n) {
        int prev = 0;
        int k = 1;

        // Phase 1: Repeated doubling to bound F
        while (k <= n && !b.drop(k)) {
            prev = k;
            k *= 2;
        }

        // Phase 2: Binary search within the identified range [prev + 1, min(k, n)]
        int lo = prev + 1;
        int hi = Math.min(k, n);
        int resultF = n + 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (b.drop(mid)) {
                resultF = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return resultF;
    }

    public static void main(String[] args) {
        int n = 1_000_000; // 1 million floors
        int f = 43;        // Target threshold floor (F << N)

        Building building = new Building(n, f);

        // Run Strategy 1: Standard Binary Search
        int foundF1 = findFloorBinarySearch(building, n);
        int throws1 = building.getThrowsCount();
        int broken1 = building.getBrokenCount();

        building.resetStats();

        // Run Strategy 2: Exponential Search (Repeated Doubling)
        int foundF2 = findFloorExponentialSearch(building, n);
        int throws2 = building.getThrowsCount();
        int broken2 = building.getBrokenCount();
        StdOut.printf("Building height (N): %,d | Target Floor (F): %d\n", n, f);
        StdOut.println("---------------------------------------------------------------");
        StdOut.printf("Strategy 1 (Binary Search)     : Discovered F = %d | Throws: %d | Broken Eggs: %d\n",
                      foundF1, throws1, broken1);
        StdOut.printf("Strategy 2 (Exponential Search): Discovered F = %d | Throws: %d | Broken Eggs: %d\n",
                      foundF2, throws2, broken2);
    }
}