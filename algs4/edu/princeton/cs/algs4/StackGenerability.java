package edu.princeton.cs.algs4;

public class StackGenerability {

    /**
     * Part 1: Determines whether an intermixed sequence of push (+) and pop (-) operations
     * causes stack underflow.
     *
     * Time Complexity:  O(N)
     * Space Complexity: O(1) - uses only a single integer counter, independent of N.
     *
     * @param operations array of strings representing operations ("+" for push, "-" for pop)
     * @return true if stack underflows at any point, false otherwise
     */
    public static boolean willUnderflow(String[] operations) {
        int count = 0; // Tracks current number of items on the stack
        for (String op : operations) {
            if (op.equals("-")) {
                count--;
            } else { // Any non-minus token represents a push directive
                count++;
            }

            if (count < 0) {
                return true; // Underflow occurred: pop attempted on an empty stack
            }
        }
        return false;
    }

    /**
     * Part 2: Determines whether a given permutation of {0, 1, ..., N-1} can be generated
     * by pushing 0, 1, ..., N-1 in increasing order onto a stack and intermixing pop operations.
     *
     * Time Complexity:  O(N) - each element is pushed and popped at most once.
     * Space Complexity: O(N) - auxiliary stack storage.
     *
     * @param permutation array containing the target permutation of 0..N-1
     * @return true if the permutation is stack-generable, false otherwise
     */
    public static boolean isGenerable(int[] permutation) {
        if (permutation == null) {
            return false;
        }

        int n = permutation.length;
        Stack<Integer> stack = new Stack<Integer>();
        int nextPush = 0; // Next integer in sequence 0..N-1 to be pushed

        for (int target : permutation) {
            // Push values onto the stack until target is on top
            while (nextPush <= target && nextPush < n) {
                stack.push(nextPush);
                nextPush++;
            }

            // If the top item matches target, pop it; otherwise, the permutation is invalid
            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        StdOut.println("--- Part 1: Stack Underflow Detection ---");
        // Test 1: Valid sequence (0 1 - - 2 -)
        String[] validOps = {"0", "1", "-", "-", "2", "-"};
        StdOut.println("Sequence [0, 1, -, -, 2, -] underflows? " + willUnderflow(validOps)); // false

        // Test 2: Invalid sequence causing underflow (- 0 1 -)
        String[] invalidOps = {"-", "0", "1", "-"};
        StdOut.println("Sequence [-, 0, 1, -] underflows?       " + willUnderflow(invalidOps)); // true

        StdOut.println("\n--- Part 2: Permutation Generability ---");
        // Test 1: Valid permutation [4, 3, 2, 1, 0]
        int[] perm1 = {4, 3, 2, 1, 0};
        StdOut.println("Is [4, 3, 2, 1, 0] generable? " + isGenerable(perm1)); // true

        // Test 2: Valid permutation [2, 5, 6, 7, 4, 3, 9, 8, 1, 0]
        int[] perm2 = {2, 5, 6, 7, 4, 3, 9, 8, 1, 0};
        StdOut.println("Is [2, 5, 6, 7, 4, 3, 9, 8, 1, 0] generable? " + isGenerable(perm2)); // true

        // Test 3: Invalid permutation [4, 6, 8, 7, 5, 3, 2, 9, 0, 1]
        // Explanation: 1 was pushed before 0, so 0 must be popped before 1
        int[] perm3 = {4, 6, 8, 7, 5, 3, 2, 9, 0, 1};
        StdOut.println("Is [4, 6, 8, 7, 5, 3, 2, 9, 0, 1] generable? " + isGenerable(perm3)); // false
    }
}

// Analysis & Questions Answered
// Part 1: Underflow Detection in O(1) Space
// - Core Condition: Underflow occurs if and only if at any prefix of the operation
//   sequence, the total number of pop (-) directives strictly exceeds the total number of
//   push directives.
// - Algorithm: Maintain an integer counter count initialized to 0. Iterate left-to-right across
//   the operation sequence:
//   - Increment count for each push
//   - Decrement count for each pop.
//   - If count drops below 0, immediately report underflow.
// - Complexity: Time complexity is linear O(N) across N total directives. Space complexity
//   is strict O(1) as it requires no dynamic collectons or memory allocation.
//
//  Part2: Permutation Generability in O(N) Time
//  - Core Condition: A permutation is stack-generable if and only if it contains no prohibited
//    pattern where a < b < c appear in input order a, b, c, but outputting c first forces a to
//    be output before b.
//  - Simulation Algorithm: Since pushing is strictly ordered(0, 1, ..., N - 1), the sequence
//    of operatons required to yield a target permutation is deterministic:
//    1. For each target integer in the output permutation, greedily push integers 0, 1, ...
//       onto an auxiliary stack until the top of the stack matches the target integer.
//    2. Pop the matching integer from the top of the stack.
//    3. If the top of the stack does not match the target (and no further integers can be pushed),
//    the permutation cannot be produced by any sequence of stack operations.
//  - Complexity: Every element 0 <= i < N is pushed onto the stack exactly once and popped at most once.
//    The total number of stack operations across the entire algorithm is bounded by 2N, guaranteeing linear
//    O(N) execution time and O(N) space.