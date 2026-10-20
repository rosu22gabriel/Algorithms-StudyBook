package edu.princeton.cs.algs4;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first; // left end
    private Node last;  // Right end
    private int n;      // Number of items

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
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

    public void pushLeft(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmptyBeforePush(oldFirst)) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        n++;
    }

    private boolean isEmptyBeforePush(Node oldNode) {
        return oldNode == null;
    }

    public void pushRight(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmptyBeforePush(oldLast)) { 
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    public Item popLeft() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        return item;
    }

    public Item popRight() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        } 
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.pushLeft("B");
        deque.pushLeft("A");
        deque.pushRight("C");
        deque.pushRight("D");

        StdOut.print("Deque (left to right): ");
        for (String s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println(); // Output: A B C D

        StdOut.println("Pop left: " + deque.popLeft()); // A
        StdOut.println("Pop right: " + deque.popRight());  // D

        StdOut.print("Remaining: ");
        for (String s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println(); // Output: B C

    }

}