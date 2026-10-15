package edu.princeton.cs.algs4;

public class QueuePermutationCheck {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        
        StdOut.print("Simulated Queue Dequeue Output: ");

        // Example intermixed pattern: enqueue then immediate dequeue
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();
    }
}