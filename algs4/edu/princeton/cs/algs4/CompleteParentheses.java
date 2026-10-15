package edu.princeton.cs.algs4;

public class CompleteParentheses {
    public static void main(String[] args) {
        Stack<String> vals = new Stack<String>();
        Stack<String> ops = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();

            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                ops.push(token);
            } else if (token.equals(")")) {
                String op = ops.pop();
                String v2 = vals.pop();
                String v1 = vals.pop();
                String subExpr = "( " + v1 + " " + op + " " + v2 + " )";
                vals.push(subExpr); 
            } else {
                vals.push(token);
            }
        }
        StdOut.println(vals.pop());
    }
}