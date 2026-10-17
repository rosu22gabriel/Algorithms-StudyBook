package edu.princeton.cs.algs4;

public class LinkedListRemove {
    public static class Node {
        String item;
        Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public static Node remove(Node first, String key) {
        // Remove matching nodes from the beginning of the list
        while (first != null && (key == null ? first.item == null : key.equals(first.item))) {
            first = first.next;
        }

        // Remove matching nodes from the remainder of the list 
        Node current = first;
        while (current != null && current.next != null) {
            if (key == null ? current.next.item == null : key.equals(current.next.item)) {
                current.next = current.next.next; // Bypass matching node
            } else {
                current = current.next;
            }
        }

        return first;
    }

    public static void printList(Node first) {
        if (first == null) {
            StdOut.println("(empty list)");
            return;
        }
        for (Node current = first; current != null; current = current.next) {
            StdOut.print(current.item + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // Construct list: "a" -> "b" -> "a" -> "c" -> "a" -> null
        Node n5 = new Node("a", null);
        Node n4 = new Node("c", n5);
        Node n3 = new Node("a", n4);
        Node n2 = new Node("b", n3);
        Node n1 = new Node("a", n2);

        StdOut.print("Original list: ");
        printList(n1); // Output: a b a c a

        // Remove all occurrences of "a"
        n1 = remove(n1, "a");
        StdOut.print("After remove(\"a\"):");
        printList(n1); // Output: b c

        // Attempt removing key not present
        n1 = remove(n1, "z");
        StdOut.print("After remove(\"z\": ");
        printList(n1); // Output: b c
    }
}