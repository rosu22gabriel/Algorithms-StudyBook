package edu.princeton.cs.algs4;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {
    private Node first; // Top of the steque
    private Node last;  // Bottom of the steque
    private int n;      // Number of elements

    private class Node {
        Item item;
        Node next;
    }

    public Steque() {
        first = null;
        last  = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    /**
     * Stack operation: Adds item to the top (front) of the steque.
     */
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (isEmptyBeforePush(oldFirst)) {
            last = first;
        }
        n++;
    }

    private boolean isEmptyBeforePush(Node oldFirst) {
        return oldFirst == null;
    }

    /**
     * Stack/Queue operation: Removes and returns item from the top (front).
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Steque underflow");
        }
        Item item = first.item;
        first = first.next;
        n--; 
        if (isEmpty()) {
            last = null; // Prevent loitering reference
        }
        return item;
    }

    /**
     * Queue operation: Adds item to the bottom (back) of the steque.
     */
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    private class StequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Steque<String> steque = new Steque<String>();

        // Push elements to top
        steque.push("B");
        steque.push("A"); // State: A -> B

        // Enqueue element to bottom
        steque.enqueue("C"); // State: A -> B -> C

        StdOut.print("Steque elements (top to bottom): ");
        for (String s : steque) {
            StdOut.print(s + " ");
        }
        StdOut.println(); // Output: A B C

        StdOut.println("Popped top: " + steque.pop()); // Output: A
        StdOut.println("Popped top: " + steque.pop()); // Output: B
        StdOut.println("Popped top: " + steque.pop()); // Output: C
        StdOut.println("Is empty: " + steque.isEmpty()); // Output: true
    }
}