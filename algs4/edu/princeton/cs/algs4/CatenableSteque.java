package edu.princeton.cs.algs4;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CatenableSteque<Item> implements Iterable<Item> {
    private Node last; // Points to the last node in circular list (last.next is head)
    private int n;     // Number of items in the steque

    private class Node {
        Item item;
        Node next;
    }

    public CatenableSteque() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return last == null;
    }

    /**
     * Stack operation: Adds items to te top (front / head)
     */
    public void push(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            node.next = node;
            last = node;
        } else {
            node.next = last.next;
            last.next = node;
        }
        n++;
    }

    /**
     * Stack/Queue operation: Removes and returns item from top (front / head).
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Steque underflow");
        Node first = last.next;
        Item item = first.item;
        if (last == first) { // Single element
            last = null;
        } else {
            last.next = first.next;
        }
        n--;
        return item;
    }

    /**
     * Queue operation: Adds item to the bottom (back / tail).
     */
    public void enqueue(Item item) {
        push(item);
        last = last.next; // Advance tail pointer to newly pushed node
    }

    /**
     * Destructively catenates another steque onto the bottom of this steque in O(1) time.
     * After this call, 'that' becomes empty.
     */
    public void catenate(CatenableSteque<Item> that) {
        if (that == null || that.isEmpty()) {
            return;
        }
        if (this.isEmpty()) {
            this.last = that.last;
            this.n = that.n;
        } else {
            Node firstThis = this.last.next;
            Node firstThat = that.last.next;

            this.last.next = firstThat; // Tail of 'this' connects to head of 'that'
            that.last.next = firstThis; // Tail of 'that' wraps back to the head of 'this'
            this.last = that.last;      // Update tail pointer to the end of 'that'
            this.n += that.n;
        }

        // Empty the source steque (destructive catenation)
        that.last = null;
        that.n = 0;
    }

    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    private class StequeIterator implements Iterator<Item> {
        private Node current = (last == null) ? null : last.next;
        private int count = 0;

        public boolean hasNext() {
            return count < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            count++;
            return item; 
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        StdOut.println("--- 1. Queue Catenation Test ---");
        CatenableSteque<String> q1 = new CatenableSteque<String>();
        q1.enqueue("A");
        q1.enqueue("B");

        CatenableSteque<String> q2 = new CatenableSteque<String>();
        q2.enqueue("C");
        q2.enqueue("D");

        q1.catenate(q2);

        StdOut.print("Combined Queue (q1 + q2): ");
        for (String s : q1) {
            StdOut.print(s + " ");
        }
        StdOut.println(); // Output: A B C D
        StdOut.println("q2 is empty after catenation: " + q2.isEmpty()); // true

        StdOut.println("\n--- 2. Stack/Steque Push/Pop Test ---");
        CatenableSteque<String> s1 = new CatenableSteque<String>();
        s1.push("2");
        s1.push("1");

        CatenableSteque<String> s2 = new CatenableSteque<String>();
        s2.enqueue("3");
        s2.enqueue("4");

        s1.catenate(s2);

        StdOut.print("Combined Steque elements (top to bottom): ");
        while (!s1.isEmpty()) {
            StdOut.print(s1.pop() + " ");
        }
        StdOut.println(); // Output: 1 2 3 4
    }
}