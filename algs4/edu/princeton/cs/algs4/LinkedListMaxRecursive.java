package edu.princeton.cs.algs4;

public class LinkedListMaxRecursive {
    public static class Node {
        int item;
        Node next;

        public Node(int item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Recursively finds the maximum key value in a linkde list of positive integers
     * Returns 0 if the list is empty.
     * 
     * @param first reference to the current node in the linked list
     * @return maximum key value in the list, or 0 if list is empty
     */
    public static int max(Node first) {
        if (first == null) {
            return 0;
        }
        return Math.max(first.item, max(first.next));
    }

    public static void main(String[] args) {
        // Construct list: 10 -> 45 -> 23 -> 8 -> null
        Node n4 = new Node(8, null);
        Node n3 = new Node(23, n4);
        Node n2 = new Node(45, n3);
        Node n1 = new Node(10, n2);

        StdOut.println("Max in list (10 -> 45 -> 23 -> 8): " + max(n1)); // Output: 45
        StdOut.println("Max in empty list: " + max(null));
    }
}