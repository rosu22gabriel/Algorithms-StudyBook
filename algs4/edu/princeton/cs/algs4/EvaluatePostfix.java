package edu.princeton.cs.algs4;

public class EvaluatePostfix {
    public static void main(String[] args) {
        Stack<Double> stack = new Stack<Double>();

        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();

            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("-")) {
                double sub = stack.pop();
                stack.push(stack.pop() - sub);
            } else if (token.equals("/")) {
                double divisor = stack.pop();
                stack.push(stack.pop() / divisor);
            } else {
                // Token is an operand
                stack.push(Double.parseDouble(token));
            }
        }

        StdOut.println(stack.pop());
    }
}