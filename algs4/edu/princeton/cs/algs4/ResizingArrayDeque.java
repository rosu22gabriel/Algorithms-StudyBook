package edu.princeton.cs.algs4;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayDeque<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;      // Number of items
    private int first;  // Index of left-most item

    @SuppressWarnings("unchecked")
    public ResizingArrayDeque() {
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
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
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
    }

    public void pushLeft(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (n == q.length) resize(2 * q.length);
        first = (first - 1 + q.length) % q.length;
        q[first] = item;
        n++;
    }

    public void pushRight(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (n == q.length) resize(2 * q.length);
        int rightIndex = (first + n) % q.length;
        q[rightIndex] = item;
        n++;
    }

    public Item popLeft() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = q[first];
        q[first] = null; // Prevent loitering
        first = (first + 1) % q.length;
        n--;
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    public Item popRight() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        int rightIndex = (first + n - 1) % q.length;
        Item item = q[rightIndex];
        q[rightIndex] = null; // Prevent loitering
        n--;
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(first + i) % q.length];
            i++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizingArrayDeque<String> deque = new ResizingArrayDeque<String>();
        deque.pushLeft("B");
        deque.pushLeft("A");
        deque.pushRight("C");
        deque.pushRight("D");

        StdOut.print("ResizingArrayDeque (left to right): ");
        for (String s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println(); // Output: A B C D

        StdOut.println("Pop left: " + deque.popLeft()); // A
        StdOut.println("Pop right: " + deque.popRight()); // D

        StdOut.print("Remaining: ");
        for (String s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println(); // Output: B C 
    }
}