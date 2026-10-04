package edu.princeton.cs.algs4;

public class Tabulate {

    public static void tabulateData() {
        // print table
        StdOut.printf("%-12s|%8s|%8s|%8s%n", "Name", "Value1", "Value2", "Ratio");
        StdOut.println("--------------------------------------------------------");

        // Read line by line until End Of File (Ctrl + D/ Ctrl + Z or redirect file)
        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            int val1 = StdIn.readInt();
            int val2 = StdIn.readInt();

            // Cast value to double to prevent integer truncation
            double ratio = (double) val1 / val2;
        
            // Formatted row output
            StdOut.printf("%-12s|%8d|%8d|%8.3f%n", name, val1, val2, ratio);
        }        
    }

    public static void main(String args[]) {
        tabulateData();
    }

}