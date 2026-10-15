package edu.princeton.cs.algs4;

public class StackCopy {
    public static Stack<String> copy(Stack<String> stack) {
        Stack<String> temp = new Stack<String>();

        for(String s : stack) {
            temp.push(s);
        }

        Stack<String> copy = new Stack<String>();
        for (String s : temp) {
            copy.push(s);
        }

        return copy;
    }

    public static void main(String[] args) {
        Stack<String> original = new Stack<String>();
        original.push("first");
        original.push("second");
        original.push("third");

        Stack<String> copied = copy(original);

        StdOut.println("Original stack contents (top to bottom): ");
        for (String s : original) {
            StdOut.println(s + " ");
        }
        StdOut.println();

        StdOut.print("Copied stack contents (top to bottom):   ");
        for (String s : copied) {
            StdOut.print(s + " ");
        }
        StdOut.println();

        // Mutate original to verify independence
        original.pop();
        StdOut.println("\nAfter popping top element from original stack:");
        StdOut.println("Original size: " + original.size());
        StdOut.println("Copied size:   " + copied.size());
    }
}