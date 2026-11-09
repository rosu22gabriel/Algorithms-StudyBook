package edu.princeton.cs.algs4;

public class findLocalMinimum {
    public static class LocalMinResult {
        public final int row;
        public final int col;
        public final int value;

        public LocalMinResult(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    /**
     * Finds a local minimum in an N x N matrix in O(N) time.
     * 
     * @param matrix N x N matrix of distinct integers
     * @return LocalMinResult containing row, column, and value 
     */
    public static LocalMinResult findLocalMinResult(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        } 

        int numRows = matrix.length;
        int left = 0;
        int right = matrix[0].length - 1;

        while (left <= right) {
            int midCol = left + (right - left) / 2;

            // Find row index of the minimum element in midCol
            int minRow = 0;
            for (int r = 1; r < numRows; r++) {
                if (matrix[r][midCol] < matrix[minRow][midCol]) {
                    minRow = r;
                }
            }

            int currentVal = matrix[minRow][midCol];

            // Determine left and right neighbors
            boolean leftSmaller = (midCol > 0) && (matrix[minRow][midCol - 1] < currentVal);
            boolean rightSmaller = (midCol < matrix[0].length - 1) && (matrix[minRow][midCol + 1] < currentVal);

            if (!leftSmaller && !rightSmaller) {
                // Current entry is strictly smaller than all 44 neighbors
                return new LocalMinResult(minRow, midCol, currentVal);
            } else if (leftSmaller) {
                // Search left half of matrix
                right = midCol - 1;
            } else {
                // Search right half of matrix
                left = midCol + 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            In in = new In(args[0]);
            int n = in.readInt();
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = in.readInt();
                }
            }

            LocalMinResult result = findLocalMinResult(matrix);
            if (result != null) {
                StdOut.printf("Local minimum found at [%d, %d] = %d\n", result.row, result.col, result.value);
            }
        } else {
            // Sample test matrix
            int[][] testMatrix = {
                {30, 100, 20, 19, 18},
                {29, 101, 15, 102, 17},
                {28, 103, 14, 104, 16},
                {27, 105, 13, 106, 12},
                {16, 25, 24, 23, 11}
            };

            LocalMinResult result =findLocalMinResult(testMatrix);

            StdOut.println("Sample Matrix size: " + testMatrix.length + "x" + testMatrix[0].length);
            if (result != null) {
                StdOut.printf("Local Minimum found at Row %d, Col %d (Value: %d)\n", 
                              result.row, result.col, result.value);
            }
        }
    }
}
