package edu.princeton.cs.algs4;

public class LinkedListDelete<Item> {
    private Node first;
    private int size;

    private class Node {
        Item item;
        Node next;
    }

    public void add (Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    /**
     * Deletes the k-th element in the linked list (1-indexed).
     * If k <= 0, k > size, or list is empty, leaves the list unchanged.
     * 
     * @param k position of element to delete (1 for the first element)
     */
    public void delete(int k) {
        if (k <= 0 || first == null) return;

        // Removing the head node (k = 1)
        if (k == 1) {
            first = first.next;
            size--;
            return;
        }

        // Traverse to the (k - 1)-th node
        Node current = first;
        for (int i = 0; current != null && i < k - 1; i++) {
            current = current.next;
        }

        // Check if k-th element exists
        if (current == null || current.next == null) {
            return;
        }

        // Unlink the k-th node
        current.next = current.next.next;
        size--;
    }

    public void printList() {
        for (Node current = first; current != null; current = current.next) {
            StdOut.print(current.item + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        LinkedListDelete<String> list = new LinkedListDelete<String>();
        list.add("E");
        list.add("D");
        list.add("C");
        list.add("B");
        list.add("A");


        StdOut.print("Original list: ");
        list.printList(); // A B C D E

        StdOut.print("After deleting 3rd element: ");
        list.delete(3);
        list.printList(); // A B D E

        StdOut.print("After deleting 1st element: ");
        list.delete(1);
        list.printList(); // B D E

        StdOut.print("After attempting to delete 10th element (out of bounds): ");
        list.delete(10);
        list.printList(); // B D E
    }
}