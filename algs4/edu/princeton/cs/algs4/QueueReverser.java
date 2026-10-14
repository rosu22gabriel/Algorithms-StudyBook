package edu.princeton.cs.algs4;

public class QueueReverser {
    /**
     * Reverses the order of the element in queue q in-place using an auxiliary stack.
     */
    public static <Item> void reverse(Queue<Item> q) {
        Stack<Item> stack = new Stack<Item>();

        while (!q.isEmpty()) {
            stack.push(q.dequeue());
        }

        while (!stack.isEmpty()) {
            q.enqueue(stack.pop());
        }
    }


    /**
     * Main test client verifying queue reversal behaviour
     */
    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();
        q.enqueue("first");
        q.enqueue("second");
        q.enqueue("third");
        q.enqueue("fourth");

        StdOut.print("Original Queue (Front to Back): ");
        for (String item : q) {
            StdOut.print(item + " ");
        }
        StdOut.println();

        // Perform reversal
        reverse(q);

        StdOut.print("Reversed Queue (Front to Back): ");
        for (String item : q) {
            StdOut.print(item + " "); 
        }
        StdOut.println();
    }
}