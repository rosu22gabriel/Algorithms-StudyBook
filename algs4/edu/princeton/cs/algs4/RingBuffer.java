package edu.princeton.cs.algs4;

import java.util.NoSuchElementException;

public class RingBuffer<Item> {
    private Item[] a;
    private int n;      // Number of elements in the buffer
    private int first;  // Index of the oldest element (head)
    private int last;   // Index of the next available write slot (tail)

    @SuppressWarnings("unchecked")
    public RingBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        a = (Item[]) new Object[capacity];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == a.length;
    }

    public int size() {
        return n;
    }

    public int capacity() {
        return a.length;
    }

    /**
     * Inserts an item into the ring buffer.
     * Throws RuntimeException if the buffer is full/
     */
    public void enqueue(Item item){ 
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow: buffer is full");
        }
        a[last] = item;
        last = (last + 1) % a.length;   // Circular wrap-around
        n++;
    }

    /**
     * Removes and returns the oldest item from the ring buffer.
     * Throws NoSuchElementException if the buffer is empty.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring buffer underflow: buffer is empty");
        }
        Item item = a[first];
        a[first] = null; // Prevent loitering
        first = (first + 1) % a.length; // Circular wrap-around
        n--;
        return item;
    }

    public static void main(String[] args) {
        int capacity = 5;
        RingBuffer<String> buffer = new RingBuffer<String>(capacity);

        // Fill buffer to capacity
        buffer.enqueue("10");
        buffer.enqueue("20");
        buffer.enqueue("30");
        buffer.enqueue("40");
        buffer.enqueue("50");

        StdOut.println("Is full? " + buffer.isFull()); // Output: true

        // Dequeue two items
        StdOut.println("Dequeued: " + buffer.dequeue()); // Output: 10
        StdOut.println("Dequeued: " + buffer.dequeue()); // Output: 20

        // Enqueue two items to demonstrate circular index wrap-around
        buffer.enqueue("60");
        buffer.enqueue("70");

        StdOut.println("Is full after warp-around enqueue? " + buffer.isFull()); // Output: true

        // Flush remaining buffer
        StdOut.print("Remaining items in FIFO order: ");
        while (!buffer.isEmpty()) {
            StdOut.print(buffer.dequeue() + " ");
        }
        StdOut.println(); // Output: 30 40 50 60 70
    }
}