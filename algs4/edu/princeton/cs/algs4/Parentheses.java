package edu.princeton.cs.algs4;

public class Parentheses {


    private static final char LEFT_PAREN    = '(';
    private static final char RIGHT_PAREN   = ')';
    private static final char LEFT_BRACE    = '{';
    private static final char RIGHT_BRACE   = '}';
    private static final char LEFT_BRACKET  = '[';
    private static final char RIGHT_BRACKET  = ']';

    /**
     * Determines wether the given string has properly balanced delimiters.
     */
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == LEFT_PAREN || c == LEFT_PAREN || c == LEFT_BRACKET) {
                stack.push(c);
            } else if (c == RIGHT_PAREN) {
                if (stack.isEmpty() || stack.pop() != LEFT_PAREN) {
                    return false;
                }
            } else if (c == RIGHT_BRACE) {
                if (stack.isEmpty() || stack.pop() != LEFT_BRACE) {
                    return false;
                }
            } else if (c == RIGHT_BRACKET) {
                if (stack.isEmpty() || stack.pop() != LEFT_BRACKET) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * Main test client reading from Standard Input or executing sample test cases.
     */
    public static void main(String[] args) {
        if (!StdIn.isEmpty()) {
            String input = StdIn.readAll().trim();
            StdOut.println(isBalanced(input));
        } else {
            // Interactive/automated test suite
            String[] testCases = { 
                "[()]{}{[()()]()}",
                "[(])",
                "{[()]}",
                "((()))",
                "(()",
                ")(",
                ""
            };

            StdOut.println("Test Case Results");
            for (String test : testCases) {
                StdOut.printf("%-20s -> %b\n", "\"" + test + "\"", isBalanced(test));
                
            }
        }
    }


}
