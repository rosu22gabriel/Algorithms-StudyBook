package edu.princeton.cs.algs4;

public class DoublyLinkedList<Item> {
    // Nested class DoubleNode as specified by Exercise 1.3.31
    public static class DoubleNode<Item> {
        public Item item;
        public DoubleNode<Item> prev;
        public DoubleNode<Item> next;

        public DoubleNode() {}
        public DoubleNode(Item item) {
            this.item = item;
        }
    }

    // 1. Insert at the beginning
    public static <Item> DoubleNode<Item> insertFirst(DoubleNode<Item> first, Item item) {
        DoubleNode<Item> newNode = new DoubleNode<Item>(item);
        if (first == null) {
            return newNode;
        }
        DoubleNode<Item> current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.prev = current;
        return first;
    }

    // 2. Insert at the end
    public static <Item> DoubleNode<Item> insertLast(DoubleNode<Item> first, Item item) {
        DoubleNode<Item> newNode = new DoubleNode<Item>(item);
        if (first == null) {
            return newNode;
        }
        DoubleNode<Item> current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.prev = current;
        return first;
    }


    // 3. Remove from the beginning
    public static <Item> DoubleNode<Item> removeFirst(DoubleNode<Item> first) {
        if (first == null) {
            return null;
        }
        DoubleNode<Item> newFirst = first.next;
        if (newFirst != null) {
            newFirst.prev = null;
        }
        first.next = null; // Avoid loitering
        return newFirst;
    }

    // 4. Remove from the end
    public static <Item> DoubleNode<Item> removeLast(DoubleNode<Item> first) {
        if (first == null || first.next == null) {
            return null;
        }
        DoubleNode<Item> current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.prev.next = null;
        current.prev = null; // Avoid loitering
        return first;
    }

    // 5. Insert before a given node
    public static <Item> DoubleNode<Item> insertBefore(DoubleNode<Item> targetNode, Item item) {
        if (targetNode == null) {
            return null;
        }
        DoubleNode<Item> newNode = new DoubleNode<Item>(item);
        newNode.next = targetNode;
        newNode.prev = targetNode.prev;

        if (targetNode.prev != null) {
            targetNode.prev.next = newNode;
        }
        targetNode.prev = newNode;
        return newNode;
    }

    // 6. Insert after a given node
    public static <Item> DoubleNode<Item> insertAfter(DoubleNode<Item> targetNode, Item item) {
        if (targetNode == null) {
            return null;
        }
        DoubleNode<Item> newNode = new DoubleNode<Item>(item);
        newNode.prev = targetNode;
        newNode.next = targetNode.next;

        if (targetNode.next != null) {
            targetNode.next.prev = newNode;
        }
        targetNode.next = newNode;
        return newNode;
    }

    // 7. Remove a given node
    public static <Item> DoubleNode<Item> removeNode(DoubleNode<Item> targetNode) {
        if (targetNode == null) {
            return null;
        }
        DoubleNode<Item> prevNode = targetNode.prev;
        DoubleNode<Item> nextNode = targetNode.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        }
        if (nextNode != null) {
            nextNode.prev = prevNode;
        }

        targetNode.prev = null;
        targetNode.next = null;
        return nextNode != null ? nextNode : prevNode;
    }

    // Helper method to print list forward and backward to verify links
    public static <Item> void printList(DoubleNode<Item> first) {
        if (first == null) {
            StdOut.println("(empty)");
            return;
        }
        DoubleNode<Item> current = first;
        DoubleNode<Item> last = null;
        StdOut.print("Forward: ");
        while (current != null) {
            StdOut.print(current.item + " ");
            if (current.next == null) last = current;
            current = current.next;
        }
        StdOut.println();

        StdOut.print("Backward: ");
        current = last;
        while (current != null) {
            StdOut.print(current.item + " ");
            current = current.prev;
        }
        StdOut.println("\n");
    }

    public static void main(String[] args) {
        DoubleNode<String> head = null;

        StdOut.println("--- 1. Insert First (B then A) ---");
        head = insertFirst(head, "B");
        head = insertFirst(head, "A");
        printList(head); // A B

        StdOut.println("--- 2. Insert Last (C then D) ---");
        head = insertLast(head, "C");
        head = insertLast(head, "D");
        printList(head); // A B C D


        StdOut.println("--- 3. Insert After B (X) & Insert Before D (Y) ---");
        DoubleNode<String> nodeB = head.next;
        DoubleNode<String> nodeD = head.next.next.next;
        insertAfter(nodeB, "X");
        insertBefore(nodeD, "Y");
        printList(head); // A B X C Y D

        StdOut.println("--- 4. Remove First ---");
        head = removeFirst(head);
        printList(head); // B X C Y D

        StdOut.println("--- 5. Remove Last ---");
        head = removeLast(head);
        printList(head); // B X C Y

        StdOut.println("--- 6. Remove Specific Node (X) ---");
        DoubleNode<String> nodeX = head.next;
        removeNode(nodeX);
        printList(head); // B C Y
    }
}