package edu.princeton.cs.algs4;

public class KthFromLast {
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Usage: java KthFromLast<k>");
            return; 
        }
        int k = Integer.parseInt(args[0]);
        Queue<String> queue = new Queue<String>();
        
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            queue.enqueue(item);
            if(queue.size() > k) {
                queue.dequeue();
            }
        }
        if (queue.size() == k) {
            StdOut.println(queue.dequeue());
        } else {
            StdOut.println("Input contains fewer than " + k + " items.");
        }
    }
}