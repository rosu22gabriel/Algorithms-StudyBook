package edu.princeton.cs.algs4;

import java.util.NoSuchElementException;

public class Buffer {
    private Stack<Character> leftStack; // Characters to the left of the cursor
    private Stack<Character> rightStack; // Characters to the right of the cursor

    /**
     * Creates an empty text editor buffer.
     */
    public Buffer() { 
        leftStack = new Stack<Character>();
        rightStack = new Stack<Character>();
    }

    /**
     * Inserts character c at the cursor position.
     * 
     * @param c character to insert
     */
    public void insert(char c) {
        leftStack.push(c);
    }

    /**
     * Deletes and returns the character at the cursor position.
     * 
     * @return deleted character
     */
    public char delete() {
        if (!leftStack.isEmpty()) {
            return leftStack.pop();
        } else if (!rightStack.isEmpty()) {
            return rightStack.pop(); 
        } else {
            throw new NoSuchElementException("Buffer underflow: text buffer is empty");
        }
    }

    /**
     * Moves the cursor k positions to the left.
     * 
     * @param k number of positions to move
     */
    public void left(int k) {
        if (k < 0) throw new IllegalArgumentException("k must be non-negative");
        for (int i = 0; i < k && !leftStack.isEmpty(); i++) {
            rightStack.push(leftStack.pop());
        }
    }

    /**
     * Moves the cursor k positions to the right.
     * 
     * @param k number of positions to move
     */
    public void right(int k) {
        if (k < 0) throw new IllegalArgumentException("k must be non-negative");
        for (int i = 0; i < k && !rightStack.isEmpty(); i++) {
            leftStack.push(rightStack.pop());
        }
    }

    /**
     * Returns the total number of characters in the buffer.
     * 
     * @return buffer character count
     */
    public int size() {
        return leftStack.size() + rightStack.size();
    }

    /**
     * Helper method to output current buffer state with cursor position marked as '|'/
     */
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Print characters left of cursor
        Stack<Character> temp = new Stack<Character>();
        for (char c : leftStack) {
            temp.push(c);
        }
        while (!temp.isEmpty()) {
            sb.append(temp.pop());
        }

        // Mark cursor boundary
        sb.append('|');

        // Print characters right of cursor
        for (char c : rightStack) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        // Insert "HELLO"
        for (char c : "HELLO".toCharArray()) {
            buffer.insert(c);
        }
        StdOut.println("Initial buffer: " + buffer); // HELLO|

        // Move cursor left 2 positions
        buffer.left(2);
        StdOut.println("After left(2):  " + buffer); // HEL|LO

        // Insert 'X' at cursor position
        buffer.insert('X');
        StdOut.println("After insert('X'): " + buffer); // HELX|LO

        // Delete character at cursor
        char deleted = buffer.delete();
        StdOut.println("Deleted char:      '" + deleted + "'"); // 'X'
        StdOut.println("After delete(): " + buffer); // HEL/LO

        // Move right 1 position
        buffer.right(1);
        StdOut.println("After right(1): " + buffer); // HELL|O
        StdOut.println("Buffer size: " + buffer.size()); // 5
    }
}