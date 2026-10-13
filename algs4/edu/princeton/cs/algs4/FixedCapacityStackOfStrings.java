package edu.princeton.cs.algs4;

public class FixedCapacityStackOfStrings {
    private String[] a; // holds the items
    private int N;      // number of items

    /**
     * Initializes an empty stack of given capacity.
     */
    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
        N = 0;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Exercise 1.3.1: Returns true if the stack is full, false otherwise.
     */
    public boolean isFull() {
        return N == a.length;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        if (isFull()) {
            throw new RuntimeException("Stack overflow");
        }
        a[N++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return a[--N];
    }

    /**
     * Test client exercising stack operations and isFull() behavior.
     */
    public static void main(String[] args) {
        int capacity = 3;
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(capacity);

        StdOut.println("Initially empty? " + stack.isEmpty()); // true
        StdOut.println("Initially full?  " + stack.isFull());  // false

        // Fill stack to capacity
        stack.push("first");
        stack.push("second");
        StdOut.println("After 2 items - full? " + stack.isFull()); // false

        stack.push("third");
        StdOut.println("After 3 items (capacity=" + capacity + ") - full? " + stack.isFull()); // true

        // Verify pop behavior reduces fullness
        StdOut.println("Popped; " + stack.pop());
        StdOut.println("After popping 1 item - full?" + stack.isFull()); // false
    }
}