package edu.princeton.cs.algs4;

import java.util.NoSuchElementException;


public class RealTimeQueue<Item> {
    // Primary operational stacks
    private Stack<Item> L; // Main input stack (left)
    private Stack<Item> R; // Main output stack (right)
    private Stack<Item> workingL; // Buffer for enqueues while rehousing/copying

    // Helper stacks for the background state machine 
    private Stack<Item> S1; // Accumulator for the new output stack
    private Stack<Item> S2; // Temporary stack used to reverse L
    private Stack<Item> Lcopy; // Active snapshot copy of L during rehousing
    private Stack<Item> Rcopy; // Active snapshot copy of R during rehousing

    // Queue state metrics
    private int sizeL;
    private int sizeR;
    private int sizeWorkingL;

    // Incremental Copy State Machine variables
    private enum State { IDLE, COPY_R, COPY_L, DRAIN_S2 }
    private State state;
    private int elementsToCopyR;
    private int elementsToCopyL;

    public RealTimeQueue() {
        L = new Stack<Item>();
        R = new Stack<Item>();
        workingL = new Stack<Item>();

        S1 = new Stack<Item>();
        S2 = new Stack<Item>();
        Lcopy = new Stack<Item>();
        Rcopy = new Stack<Item>();

        sizeL = 0;
        sizeR = 0;
        sizeWorkingL = 0;

        state = State.IDLE;
        elementsToCopyR = 0;
        elementsToCopyL = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return sizeL + sizeR + sizeWorkingL;
    }

    /**
     * Worst-case O(1) Enqueue operation.
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");

        if (state == State.IDLE) {
            L.push(item);
            sizeL++;
        } else {
            workingL.push(item);
            sizeWorkingL++;
        }

        checkAndStartCopy();
        step();
        step(); // Advance copy state machine by 2 work steps
    }

    /**
     * Worst-case O(1) Dequeue operation.
     */
    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");

        Item item;
        if (sizeR > 0) {
            item = R.pop();
            sizeR--;
            if (state != State.IDLE && elementsToCopyR > 0) {
                elementsToCopyR--; // Account for item removed from R during active copy
            }
        } else if (state != State.IDLE && !S1.isEmpty()) {
            // R has run out during copy, retrieve from new head accumulating in S1
            item = S1.pop();
        } else {
            item = L.pop();
            sizeL--;
        }

        checkAndStartCopy();
        step();
        step(); // Advance copy state machine by 2 work steps
        return item;
    }

    /**
     * Triggers the rehousing process whenever |L| > |R|
     */
    private void checkAndStartCopy() {
        if (state == State.IDLE && sizeL > sizeR) {
            state = State.COPY_R;
            elementsToCopyR = sizeR;
            elementsToCopyL = sizeL;

            // Snapshot copies of L and R references for background transfer
            Lcopy = L;
            Rcopy = R;

            L = new Stack<Item>();
            R = new Stack<Item>();
            sizeL = 0;
            sizeR = 0;
        }
    }

    /**
     * Executes a single incremental step of the rehousing state machine.
     * Guaranteed constant number of stack operations.
     */
    private void step() {
        switch (state) {
            case COPY_R:
                if (elementsToCopyR > 0) {
                    if (!Rcopy.isEmpty()) {
                        S1.push(Rcopy.pop());
                    }
                    elementsToCopyR--;
                } else {
                    state = State.COPY_L;
                }
                break;

            case COPY_L:
                if (elementsToCopyL > 0) {
                    if (!Lcopy.isEmpty()) {
                        S2.push(Lcopy.pop());
                    }
                    elementsToCopyL--;
                } else {
                    state = State.DRAIN_S2;
                }
                break;

            case DRAIN_S2:
                if (!S2.isEmpty()) {
                    S1.push(S2.pop());
                } else {
                    // Copy finished! S1 now represents the new R stack
                    R = S1;
                    sizeR = R.size();
                    L = workingL;
                    sizeL = sizeWorkingL;
                    
                    workingL = new Stack<Item>();
                    S1 = new Stack<Item>();
                    sizeWorkingL = 0;

                    state = State.IDLE;
                }
                break;

            case IDLE:
                default:
                    break;
        }

    }

    public static void main(String[] args) {
        RealTimeQueue<Integer> q = new RealTimeQueue<Integer>();
    
        StdOut.println("--- Worst-Case O(1) Real-Time Queue Test ---");
    
        // Perform interleaved enqueue/dequeue sequence
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
            StdOut.println("Enqueued: " + i +" | Queue size: " + q.size());
        }
    
        StdOut.println("\nDequeing 5 elements:");
        for (int i = 0; i < 5; i++) {
            StdOut.println("Dequeued: " + q.dequeue() + " | Remaining size: " + q.size());
        } 

        StdOut.println("\nEnqueing 100..104:");
        for (int i = 100; i < 105; i++) {
            q.enqueue(i);
        }

        StdOut.println("\nFlushing remaining queue elements:");
        while (!q.isEmpty()) {
            StdOut.println(q.dequeue() + " ");
        }
        StdOut.println();
    }
}