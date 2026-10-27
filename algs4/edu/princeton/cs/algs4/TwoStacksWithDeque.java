package edu.princeton.cs.algs4;

import java.util.NoSuchElementException;

public class TwoStacksWithDeque<Item> {
    private final Deque<Item> deque;
    private int size1;
    private int size2;

    /**
     * Initializes two empty stacks sharing a single deque.
     */
    public TwoStacksWithDeque() {
        deque = new Deque<Item>();
        size1 = 0;
        size2 = 0;
    }

    // --- Stack 1 Operations (Left End) ---

    public void push1(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        deque.pushLeft(item);
        size1++;
    }

    public Item pop1() {
        if (isEmpty1()) {
            throw new NoSuchElementException("Stack 1 underflow");
        }
        size1--;
        return deque.popLeft();
    }

    public boolean isEmpty1() {
        return size1 == 0;
    }

    public int size1() {
        return size1;
    }
    
    // --- Stack 2 Operations (Right End) ---
    public void push2(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        deque.pushRight(item);
        size2++;
    }

    public Item pop2() {
        if (isEmpty2()) {
            throw new NoSuchElementException("Stack 2 underflow");
        }
        size2--;
        return deque.popRight();
    }

    public boolean isEmpty2() {
        return size2 == 0;
    }

    public int size2() {
        return size2;
    }

    public static void main(String[] args) {
        TwoStacksWithDeque<String> stacks = new TwoStacksWithDeque<String>();

        // Operations on Stack 1
        stacks.push1("A");
        stacks.push1("B");
        stacks.push1("C");


        // Operations on Stack 2
        stacks.push2("X");
        stacks.push2("Y");

        StdOut.println("Stack 1 size: " + stacks.size1()); // Output: 3
        StdOut.println("Stack 2 size: " + stacks.size2()); // Output: 2

        // Interleaved Pops
        StdOut.println("Pop Stack 1: " + stacks.pop1()); // Output: C
        StdOut.println("Pop Stack 2: " + stacks.pop2()); // Output: Y

        StdOut.println("Pop Stack 1: " + stacks.pop1()); // Output: B
        StdOut.println("Pop Stack 2: " + stacks.pop2()); // Output: X

        StdOut.println("Stack 1 empty? " + stacks.isEmpty1()); // Output: false (A remains)
        StdOut.println("Stack 2 empty? " + stacks.isEmpty2()); // Output: true
    }
}