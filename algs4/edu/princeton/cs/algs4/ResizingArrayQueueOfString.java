package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueueOfStrings implements Iterable<String> {
    private String[] q;
    private int n;      // number of elements on queue
    private int first;  // index of first element
    private int last;   // index of next available slot

    public ResizingArrayQueueOfStrings() {
        q = new String[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        String[] temp = new String[capacity];
        for (int i = 0; i < n; i++) { 
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    public void enqueue(String item) {
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[last++] = item;
        if (last == q.length) {
            last = 0; // Wrap around
        }
        n++;
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        String item = q[first];
        q[first] = null;  // Prevent loitering
        n--;
        first++;
        if (first == q.length) {
            first = 0; // Wrap around
        }
        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }


    public Iterator<String> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<String> {
        private int i = 0;
    
        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsuppportedOperationException();
        }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            String item = q[(first + i) % q.length];
            i++;
            return item;
        }

        public static void main(String[] args) {
            ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
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
            StdOut.println("(" + queue.size() + " left on queue)");
        }
    }
}