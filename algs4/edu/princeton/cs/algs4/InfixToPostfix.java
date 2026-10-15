package edu.princeton.cs.algs4;

public class InfixToPostfix {
    private static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }

    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();

            if (token.equals("(")) {
                ops.push(token);
            } else if (token.equals(")")) {
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    StdOut.print(ops.pop() + " ");
                }
                if (!ops.isEmpty()) {
                    ops.pop(); // Discard matching '('
                }
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(token)) {
                    StdOut.print(ops.pop() + " ");
                }
                ops.push(token);
            } else {
                // Token is an operand
                StdOut.print(token + " ");
            }
        }
        while (!ops.isEmpty()) {
            StdOut.print(ops.pop() + " ");
        }
        StdOut.println();
    }
}