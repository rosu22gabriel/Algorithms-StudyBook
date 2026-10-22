package edu.princeton.cs.algs4;
import java.util.NoSuchElementException;

public class LinkedListGeneralizedQueue<Item> {
    private Node first; // Oldest item (1st least recently inserted)
    private Node last;  // Newest item
    private int n;      // Number of items in queue

    private class Node {
        Item item;
        Node next;
    }

    public LinkedListGeneralizedQueue() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Item x) {
        if (x == null) throw new IllegalArgumentException("Item cannot be null");
        Node oldLast = last;
        last = new Node();
        last.item = x;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    /**
     * Deletes and returns the k-th least recently inserted item (1-indexed).
     * 1 corresponds to the oldest item in the queue.
     */
    public Item delete(int k) {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        if (k <= 0 || k > n) throw new IllegalArgumentException("Index k out of bounds: " + k);
        
        Item item;
        if (k == 1) {
            item = first.item;
            first = first.next;
            if (first == null) last = null; // Queue is now empty
        } else {
            Node current = first;
            for (int i = 1; i < k - 1; i++) {
                current = current.next;
            }
            item = current.next.item;
            current.next = current.next.next;
            if (current.next == null) {
                last = current; // Updated tail if last element was deleted
            }
        }
        n--;
        return item;
    }

    public static void main(String[] args) {
        LinkedListGeneralizedQueue<String> queue = new LinkedListGeneralizedQueue<String>();
        queue.insert("A");  // 1st least recently
        queue.insert("B");  // 2nd least recently
        queue.insert("C");  // 3rd least recently
        queue.insert("D");  // 4th least recently

        StdOut.println("Deleted 2nd least recently (B): " + queue.delete(2)); // Output: B
        StdOut.println("Deleted 1st least recently (A): " + queue.delete(1)); // Output: A
        StdOut.println("Deleted 2nd least recently from remaining [C, D] (D): " + queue.delete(2)); // Output: D
    }
}