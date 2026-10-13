package edu.princeton.cs.algs4;

public class StackTestClient {
    public static void main(String[] args) {
        String input = "it was - the best - of times - - - it was - the - -";
        String[] tokens = input.split("\\s+");

        Stack<String> stack = new Stack<String>();

        for (String token : tokens) {
            if (!token.equals("-")) {
                stack.push(token);
            } else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}