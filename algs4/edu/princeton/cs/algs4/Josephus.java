package edu.princeton.cs.algs4;

public class Josephus {
    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println("Usage: java Josephus <N> <M>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        Queue<Integer> queue = new Queue<Integer>();

        // Initialize the circle with people 0 to N - 1
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        // Simulate elimination
        while (!queue.isEmpty()) {
            // Rotate M - 1 people to the back of the queue
            for (int i = 0; i < m - 1; i++) {
                queue.enqueue(queue.dequeue());
            }
            // Dequeue and eliminate the M-th person
            StdOut.print(queue.dequeue() + (queue.isEmpty() ? "" : " "));
        }
        StdOut.println();
    }
}