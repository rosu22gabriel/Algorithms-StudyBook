package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomBag<Item> implements Iterable<Item> {
    private Item[] bag;
    private int n; // Number of items in bag

    @SuppressWarnings("unchecked")
    public RandomBag() {
        bag = (Item[]) new Object[2];
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
            temp[i] = bag[i];
        }
        bag = temp;
    }

    public void add(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (n == bag.length) {
            resize(2 * bag.length);
        }
        bag[n++] = item;
    }

    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<Item> {
        private final Item[] copy;
        private int current;
        
        @SuppressWarnings("unchecked")
        public RandomBagIterator() {
            copy = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                copy[i] = bag[i];
            }
            StdRandom.shuffle(copy);
            current = 0;
        }

        public boolean hasNext() {
            return current < copy.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomBag<Integer> bag = new RandomBag<Integer>();
        for (int i = 1; i <= 10; i++) {
            bag.add(i);
        }

        StdOut.println("Bag size: " + bag.size());

        StdOut.print("First random pass: ");
        for (int item : bag) {
            StdOut.print(item + " ");
        }
        StdOut.println();
    }
}