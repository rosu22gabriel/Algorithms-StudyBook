package edu.princeton.cs.algs4;

public class ReadTransactionsClient {
    
    // Static method reading transactions from an input file
    public static Transaction[] readTransactions(String name) {
        In in = new In(name);
        Queue<Transaction> queue = new Queue<Transaction>();
        while (!in.isEmpty()) {
            String line = in.readLine().trim();
            if (!line.isEmpty()) {
                queue.enqueue(new Transaction(line));
            }
        }
        int n = queue.size();
        Transaction[] transactions = new Transaction[n];
        for (int i = 0; i < n; i++) {
            transactions[i] = queue.dequeue();
        }
        return transactions;
    }

    // Static method reading transactions directly from standard input (StdIn)
    public static Transaction[] readTransactions() {
        Queue<Transaction> queue = new Queue<Transaction>();
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine().trim();
            if (!line.isEmpty()) {
                queue.enqueue(new Transaction(line));
            }
        }
        int n = queue.size();
        Transaction[] transactions = new Transaction[n];
        for (int i = 0; i < n; i++) {
            transactions[i] = queue.dequeue();
        }
        return transactions;
    }

    public static void main(String[] args) {
        Transaction[] transactions;
        if (args.length > 0) {
            transactions = readTransactions(args[0]);
        } else {
            transactions = readTransactions();
        }

        StdOut.println("Successfully read " + transactions.length + " transactions:");
        for (Transaction t : transactions) {
            StdOut.println(t);
        }
    }
}