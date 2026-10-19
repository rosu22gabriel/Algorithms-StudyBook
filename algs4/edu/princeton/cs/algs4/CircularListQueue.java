package edu.princeton.cs.algs4;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularListQueue<Item> implements Iterable<Item> {
    private Node last;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    public CircularListQueue() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) {
            last = x;
            last.next = last;
        } else {
            x.next = last.next; // x.next points to first node
            last.next = x;      // old last node points to new node
            last = x;           // last updated to new node
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = last.next.item; // last.next is the first node
        if (last == last.next) {
            last = null;            // single-node queue becomes empty
        } else {
            last.next = last.next.next; // bypass the head node
        }
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
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
        CircularListQueue<String> queue = new CircularListQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-")) {
                if (!queue.isEmpty()) {
                    StdOut.print(queue.dequeue() + " ");
                }
            } else {
                queue.enqueue(item);
            }
        }
        StdOut.println("(" + queue.size() + " left on queue");
    }
}