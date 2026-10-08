package edu.princeton.cs.algs4;

public class CircularRotation {
    public static boolean isCircularRotation(String s, String t) {
        return s.length() == t.length() && (t + t).indexOf(s) >= 0;
    }

    public static void main(String[] args) {
        String s = "ACTGACG";
        String t = "TGACGAC";

        boolean result = isCircularRotation(s, t);

        StdOut.printf("s: \"%s\"\n", s);
        StdOut.printf("t: \"%s\"\n", t);
        StdOut.printf("Is cirular rotation? %b\n", result);
    } 
}