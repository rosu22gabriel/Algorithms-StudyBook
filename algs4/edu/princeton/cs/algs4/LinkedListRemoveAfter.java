package edu.princeton.cs.algs4;

public class LinkedListRemoveAfter {
    public static class Node {
        String item;
        Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }

        /**
         * Removes the node following the given node in a singly linked list.
         * Does nothing if the argument node or its next reference is null.
         * 
         * @param node the reference node whose successor should be removed
         */
        public static void removeAfter(Node node) {
            if (node == null || node.next == null) {
                return;
            }
            node.next = node.next.next;
        }

        public static void printList(Node first) {
            for (Node current = first; current != null; current = current.next) {
                StdOut.print(current.item + " ");
            }
            StdOut.println();
        }

        public static void main(String[] args) {
            // Construct linked list: "A" -> "B" -> "C" -> "D" -> null
            Node d = new Node("D", null);
            Node c = new Node("C", d);
            Node b = new Node("B", c);
            Node a = new Node("A", b);

            StdOut.print("Original list: ");
            printList(a); // A B C D

            // Remove node after 'B' (removes 'C')
            removeAfter(b);
            StdOut.print("After removeAfter(B): ");
            printList(a); // A B D

            // Remove node after 'D' (tail node, node.next is null -> does nothing)
            removeAfter(d);
            StdOut.print("After removeAfter(D): ");
            printList(a); // A B D 

            // Call removeAfter on null -> does nothing
            removeAfter(null);
            StdOut.print("After removeAfter(null): ");
            printList(a); // A B D
        }
    }
}