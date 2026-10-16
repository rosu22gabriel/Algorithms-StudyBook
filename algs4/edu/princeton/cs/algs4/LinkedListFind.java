
import edu.princeton.cs.algs4.StdOut;

public class LinkedListNode {
    public static class Node {
        String item;
        Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }


    /**
     *  Searches for a string key in a singly linked list.
     * 
     * @param first the head node of the linked list
     * @param key   the string key to search for
     * @param true  if the key is found in any node's item field; false otherwise
     */
    public static boolean find(Node first, String key) {
        for (Node x = first; x != null; x = x.next) {
            if (key == null) {
                if (x.item == null) return true;
            } else if (key.equals(x.item)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Construct linked list: "apple" -> "banana" -> "cherry" -> null
        Node third = new Node("cherry", null);
        Node second = new Node("banana", third);
        Node first = new Node("apple", second);


        StdOut.println("Search 'banana': " + find(first, "banana")); // Output: true
        StdOut.println("Search 'date':   " + find(first, "date"));   // Output: false
        StdOut.println("Search null:     " + find(null, "apple"));   // Output: false
    }
}