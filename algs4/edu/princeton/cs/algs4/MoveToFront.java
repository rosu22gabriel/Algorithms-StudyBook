package edu.princeton.cs.algs4;


public class MoveToFront {
    private Node first; // Head of the linked list
    private int n;      // Number of unique characters

    private class Node {
        char item;
        Node next;
    }

    public MoveToFront() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    /**
     * Processes a character according to Move-To-Front strategy:
     * If character is already preent, deletes it from its current position
     * and re-inserts it at the front. Otherwise, inserts it at the front.
     */
    public void insert(char c) {
        // Case 1: Character is already at the front
        if (first != null && first.item == c) {
            return;
        }

        // Case 2: Search for duplicate down the list and delete if present
        Node current = first;
        while (current != null && current.next != null) {
            if (current.next.item == c) {
                current.next = current.next.next; // Bypass existing node
                n--;
                break;
            }
            current = current.next;
        }

        // Case 3: Insert character at the front of the list
        Node oldFirst = first;
        first = new Node();
        first.item = c;
        first.next = oldFirst;
        n++;
    }

    public void printList() {
        for (Node x = first; x != null; x = x.next) {
            StdOut.print(x.item + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        MoveToFront mtf = new MoveToFront();

        while (!StdIn.isEmpty()) {
            char c = StdIn.readChar();
            // Skip newline and carriage return character for clean console input
            if (c == '\n' || c == '\r') {
                continue;
            }
            mtf.insert(c);
        }
        StdOut.print("Final list state (most recently used to least): ");
        mtf.printList();
    }
}