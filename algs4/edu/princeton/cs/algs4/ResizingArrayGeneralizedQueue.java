package edu.princeton.cs.algs4;

import java.util.NoSuchElementException;

public class ResizingArrayGeneralizedQueue<Item> {
    private Item[] q;
    private int n; // Number of items in queue

    @SuppressWarnings("unchecked")
    public ResizingArrayGeneralizedQueue() {
        q = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @SuppressWarnings("unchecked") 
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }

    public void insert(Item x) {
        if (x == null) throw new IllegalArgumentException("Item cannot be null");
        if (n == q.length) resize(2 * q.length);
        q[n++] = x;
    }

    /**
     * Deletes and returns the k-th least recently inserted item (1-indexed).
     * 1 corresponds to the oldest item in the queue.
     */
    public Item delete(int k) {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        if (k <= 0 || k > n) throw new IllegalArgumentException("Index k out of bounds: " + k);
        
        int targetIndex = k - 1; // 1-based k maps to array index k - 1
        Item item = q[targetIndex];

        // Shift items left to close the gap
        for (int i = targetIndex; i < n - 1; i++) {
            q[i] = q[i + 1];
        }
        q[n - 1] = null; // Avoid loitering
        n--;

        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    public static void main(String[] args) {
        ResizingArrayGeneralizedQueue<String> queue = new ResizingArrayGeneralizedQueue<String>();
        queue.insert("A"); // 1st least recently
        queue.insert("B"); // 2nd least recently
        queue.insert("C"); // 3rd least recently
        queue.insert("D"); // 4th least recently

        StdOut.println("Deleted 2nd least recently (B): " + queue.delete(2)); // Output: B
        StdOut.println("Deleted 1st least recently (A): " + queue.delete(1)); // Output: A
        StdOut.println("Deleted 2nd least recently from remaining [C, D] (D): " + queue.delete(2)); // Output: D
    }
}