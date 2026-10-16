package edu.princeton.cs.algs4;

public class ReadDatesClient {
    // Static metod reading dates from an input file or stream
    public static Date[] readDates(String name) {
        In in = new In(name);
        Queue<Date> queue = new Queue<Date>();
        while (!in.isEmpty()) {
            queue.enqueue(new Date(in.readString()));
        }
        int n = queue.size();
        Date[] dates = new Date[n];
        for (int i = 0; i < n; i++) {
            dates[i] = queue.dequeue();
        }
        return dates;
    }

    // Static method reading dates directly from standard input (StdIn)
    public static Date[] readDates() {
        Queue<Date> queue = new Queue<Date>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(new Date(StdIn.readString()));
        }
        int n = queue.size();
        Date[] dates = new Date[n];
        for (int i = 0; i < n; i++) {
            dates[i] = queue.dequeue();
        }
        return dates;
    }

    public static void main(String[] args) {
        Date[] dates;
        if (args.length > 0) {
            dates = readDates(args[0]);
        } else {
            dates = readDates();
        }

        StdOut.println("Succesfully read " + dates.length + " dates:");
        for (Date d : dates) {
            StdOut.println(d);
        }
    }
}