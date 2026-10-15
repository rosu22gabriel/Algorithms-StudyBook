package edu.princeton.cs.algs4;

public class DoublingStackOfStrings {
    private String[] a;
    private int N;

    public DoublingStackOfStrings() {
        a = new String[1];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public int capacity() {
        return a.length;
    }

    private void resize(int max) {
        String[] temp = new String[max];
        for (int i = 0; i < N; i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(String item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public String pop() {
        String item = a[--N];
        a[N] = null;  // Prevent loitering
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public void printArrayState() {
        StdOut.println("Array contents: [");
        for (int i = 0; i < a.length; i++) {
            if (i > 0) StdOut.print(", ");
            StdOut.print(a[i] == null ? "null" : "\"" + a[i] + "\"");
        }
        StdOut.println("]");
        StdOut.println("Array capacity (size): " + a.length);
        StdOut.println("Items on stack (N): " + N);
    }

    public static void main(String[] args) {
        DoublingStackOfStrings stack = new DoublingStackOfStrings();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(item);
            }
        }
        stack.printArrayState();
    }
}
