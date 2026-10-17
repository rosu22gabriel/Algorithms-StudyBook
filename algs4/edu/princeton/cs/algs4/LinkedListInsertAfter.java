package edu.princeton.cs.algs4;

public class LinkedListInsertAfter {

    public static class Node {
        String item;
        Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Inserts the second node after the first node in a singly linked list.
     * Does nothing if either argument is null.
     *
     * @param firstNode  the node after which insertion occurs
     * @param secondNode the node to be inserted
     */
    public static void insertAfter(Node firstNode, Node secondNode) {
        if (firstNode == null || secondNode == null) {
            return;
        }
        secondNode.next = firstNode.next;
        firstNode.next = secondNode;
    }

    public static void printList(Node first) {
        for (Node current = first; current != null; current = current.next) {
            StdOut.print(current.item + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // Construct linked list: "A" -> "C" -> null
        Node c = new Node("C", null);
        Node a = new Node("A", c);

        // Node to insert
        Node b = new Node("B", null);

        StdOut.print("Original list: ");
        printList(a); // A C 

        // Insert B after A
        insertAfter(a, b);
        StdOut.print("After insertAfter(A, B): ");
        printList(a); // A B C 

        // Insert D after C (tail node insertion)
        Node d = new Node("D", null);
        insertAfter(c, d);
        StdOut.print("After insertAfter(C, D): ");
        printList(a); // A B C D 

        // Call insertAfter with null references -> does nothing
        insertAfter(null, new Node("E", null));
        insertAfter(a, null);
        StdOut.print("After null insertion tests: ");
        printList(a); // A B C D 
    }
}