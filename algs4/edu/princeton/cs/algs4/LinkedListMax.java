package edu.princeton.cs.algs4;

public class LinkedListMax {
    public static class Node {
        int item;
        Node next;

        public Node(int item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Returns the maximum key value in a linked list of positive integers
     * Returns 0 if the list is empty.
     * 
     * @param first reference to the first node in the linked list
     * @return maximum key value, or 0 if list is empty
     */
    public static int max(Node first) {
        int maxVal = 0;
        for (Node current = first; current != null; current = current.next) {
            if (current.item > maxVal) {
                maxVal = current.item;
            }
        }
        return maxVal;
    }

    public static void main(String[] args) {
        // Construct list: 10 -> 45 -> 23 -> 8 -> null
        Node n4 = new Node(8, null);
        Node n3 = new Node(23, n4);
        Node n2 = new Node(45, n3);
        Node n1 = new Node(10, n2); 

        StdOut.println("Max in list (10 -> 45 -> 23 -> 8): " + max(n1)); // Output: 45
        StdOut.println("Max in empty list: " + max(null));               // Output:  0
    }
}