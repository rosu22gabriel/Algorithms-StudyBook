package edu.princeton.cs.algs4;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n; // Number of elements in the queue

    @SuppressWarnings("unchecked")
    public RandomQueue() {
        q = (Item[]) new Object[2];
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
            temp[i] = q[i];
        }
        q = temp;
    }

    /**
     * Adds an item to the random queue.
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[n++] = item;
    }

    /**
     * Removes and returns a random item (sample without replacement).
     * Swaps a random element with the last element in O(1) time.
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Random queue underflow");
        int r = StdRandom.uniform(n);
        Item temp = q[r];
        q[r] = q[n - 1];
        q[n - 1] = null; // Prevent loitering
        n--;
        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return temp;
    }


    /**
     * Returns a random item without removing it (sample with replacement).
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Random queue underflow");
        int r = StdRandom.uniform(n);
        return q[r];
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private final Item[] copy;
        private int current;

        @SuppressWarnings("Unchecked")
        public RandomQueueIterator() {
            copy = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                copy[i] = q[i];
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



    // Helper class to represent a playing card for the bridge client
    private static class Card {
        private final String suit;
        private final String rank;

        public Card(String suit, String rank) {
            this.suit = suit;
            this.rank = rank;
        }

        @Override 
        public String toString() {
            return rank + " of " + suit;
        }
    }

    // Client delaing bridge hands (4 players, 13 cards each)
    public static void main(String[] args) {
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        RandomQueue<Card> deck = new RandomQueue<Card>();

        // Build standard 52-card deck
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.enqueue(new Card(suit, rank));
            }
        }

        StdOut.println("Deck initialized with " + deck.size() + " cards.\n");

        // Test sample() with replacement
        StdOut.println("Sample non-destructively: " + deck.sample());
        StdOut.println("Deck size after sampling: " + deck.size() + "\n");

        // Deal 4 bridge hands of 13 cards each
        String[] players = {"North", "East", "South", "West"};
        for (String player : players) {
            StdOut.println("--- " + player + "'s Hand ---");
            for (int i = 0; i < 13; i++) {
                StdOut.println("  " + deck.dequeue());
            }
            StdOut.println();
        }

        StdOut.println("Cards left in the deck: " + deck.size());
    }
}