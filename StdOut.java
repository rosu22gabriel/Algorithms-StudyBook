


/******************************************************************************
 *  Compilation:  javac StdOut.java
 *  Execution:    java StdOut
 *  Dependencies: none
 *
 *  Writes data of various types to standard output.
 *
 ******************************************************************************/

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 *  The {@code StdOut} class provides static methods for printing strings
 *  and numbers to standard output.
 *
 *  <p><b>Getting started.</b>
 *  To use this class, you must have {@code StdOut.class} in your
 *  Java classpath. If you used our autoinstaller, you should be all set.
 *  Otherwise, either download
 *  <a href = "https://introcs.cs.princeton.edu/java/code/stdlib.jar">stdlib.jar</a>
 *  and add to your Java classpath or download
 *  <a href = "https://introcs.cs.princeton.edu/java/stdlib/StdOut.java">StdOut.java</a>
 *  and put a copy in your working directory.
 *  <p>
 *  Here is an example program that uses {@code StdOut}:
 *  <pre>
 *   public class TestStdOut {
 *       public static void main(String[] args) {
 *           int a = 17;
 *           int b = 23;
 *           int sum = a + b;
 *           StdOut.println("Hello, World");
 *           StdOut.printf("%d + %d = %d\n", a, b, sum);
 *       }
 *   }
 *  </pre>
 *  <p>
 *  <b>Differences with System.out.</b>
 *  The behavior of {@code StdOut} is similar to that of {@link System#out},
 *  but there are a few technical differences:
 *  <ul>
 *  <li> {@code StdOut} coerces the character-set encoding to UTF-8,
 *       which is a standard character encoding for Unicode.
 *  <li> {@code StdOut} coerces the locale to {@link Locale#US},
 *       for consistency with {@link StdIn}, {@link Double#parseDouble(String)},
 *       and floating-point literals.
 *  <li> {@code StdOut} <em>flushes</em> standard output after each call to
 *       {@code print()} so that text will appear immediately in the terminal.
 *  </ul>
 *  <p>
 *  <b>Reference.</b>
 *  For additional documentation,
 *  see <a href="https://introcs.cs.princeton.edu/15inout">Section 1.5</a> of
 *  <em>Computer Science: An Interdisciplinary Approach</em>
 *  by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class StdOut {

    // force Unicode UTF-8 encoding; otherwise it's system dependent
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    // assume language = English, country = US for consistency with StdIn
    private static final Locale LOCALE = Locale.US;

    // send output here
    private static PrintWriter out;

    // this is called before invoking any methods
    static {
        out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET), true);
    }

    // don't instantiate
    private StdOut() { }

   /**
     * Terminates the current line by printing the line-separator string.
     */
    public static void println() {
        out.println();
    }

   /**
     * Prints an object to this output stream and then terminates the line.
     *
     * @param x the object to print
     */
    public static void println(Object x) {
        out.println(x);
    }

   /**
     * Prints a boolean to standard output and then terminates the line.
     *
     * @param x the boolean to print
     */
    public static void println(boolean x) {
        out.println(x);
    }

   /**
     * Prints a character to standard output and then terminates the line.
     *
     * @param x the character to print
     */
    public static void println(char x) {
        out.println(x);
    }

   /**
     * Prints a double to standard output and then terminates the line.
     *
     * @param x the double to print
     */
    public static void println(double x) {
        out.println(x);
    }

   /**
     * Prints an integer to standard output and then terminates the line.
     *
     * @param x the integer to print
     */
    public static void println(float x) {
        out.println(x);
    }

   /**
     * Prints an integer to standard output and then terminates the line.
     *
     * @param x the integer to print
     */
    public static void println(int x) {
        out.println(x);
    }

   /**
     * Prints a long to standard output and then terminates the line.
     *
     * @param x the long to print
     */
    public static void println(long x) {
        out.println(x);
    }

   /**
     * Prints a short integer to standard output and then terminates the line.
     *
     * @param x the short to print
     */
    public static void println(short x) {
        out.println(x);
    }

   /**
     * Prints a byte to standard output and then terminates the line.
     * <p>
     * To write binary data, see {@link BinaryStdOut}.
     *
     * @param x the byte to print
     */
    public static void println(byte x) {
        out.println(x);
    }

   /**
     * Flushes standard output.
     */
    public static void print() {
        out.flush();
    }

   /**
     * Prints an object to standard output and flushes standard output.
     *
     * @param x the object to print
     */
    public static void print(Object x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints a boolean to standard output and flushes standard output.
     *
     * @param x the boolean to print
     */
    public static void print(boolean x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints a character to standard output and flushes standard output.
     *
     * @param x the character to print
     */
    public static void print(char x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints a double to standard output and flushes standard output.
     *
     * @param x the double to print
     */
    public static void print(double x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints a float to standard output and flushes standard output.
     *
     * @param x the float to print
     */
    public static void print(float x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints an integer to standard output and flushes standard output.
     *
     * @param x the integer to print
     */
    public static void print(int x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints a long integer to standard output and flushes standard output.
     *
     * @param x the long integer to print
     */
    public static void print(long x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints a short integer to standard output and flushes standard output.
     *
     * @param x the short integer to print
     */
    public static void print(short x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints a byte to standard output and flushes standard output.
     *
     * @param x the byte to print
     */
    public static void print(byte x) {
        out.print(x);
        out.flush();
    }

   /**
     * Prints a formatted string to standard output, using the specified format
     * string and arguments, and then flushes standard output.
     *
     *
     * @param format the <a href = "http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args   the arguments accompanying the format string
     */
    public static void printf(String format, Object... args) {
        out.printf(LOCALE, format, args);
        out.flush();
    }

   /**
     * Prints a formatted string to standard output, using the locale and
     * the specified format string and arguments; then flushes standard output.
     *
     * @param locale the locale
     * @param format the <a href = "http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args   the arguments accompanying the format string
     */
    public static void printf(Locale locale, String format, Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }

    public static int lg(int N) { 
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        int log = 0;
        while (N > 1) {
            N /= 2; // Equivalent to N >>= 1
            log++;
        }
        return log;
    }

    public static int[] histogram(int[] a, int M) {
        int[] array = new int[M];
        int ctr = 0;
        int sum = 0;
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < a.length; j++) {
                if (a[j] == i) {
                    ctr++;
                }
                array[i] = ctr;
                sum += array[i];
            }
        }
        StdOut.println(sum == a.length);
        return array;
    }

    public static String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }


   /**
     * Unit tests some methods in {@code StdOut}.
     *
     * @param args the command-line arguments
     */

    public static void main(String[] args) {

        // StdOut.printf("You go there\n" + "Where?\n" + "\nHere");
        // StdOut.print(5);
        // StdOut.print(true);
        // StdOut.print("Not True");
        // StdOut.println("Here is a line");
        // StdOut.print("Why am I not so close to this guy");
        // StdOut.println((0 + 15) / 2);
        // StdOut.println(2.0e-6 * 100000000.1);
        // StdOut.println(true && false || true && true);

        // var x = 0.0;
        // x = (1 + 2.236) / 2;
        // StdOut.println(x);
        // x = 1 + 2 + 3 + 4.0;
        // StdOut.println(x);

        // var y = true;
        // y = (4.1 >= 4);
        // StdOut.println(y);

        // var z = 1 + 2 + "3";
        // StdOut.println(z);

        // var a = 1;
        // var b = 2;
        // var c = 1;

        // then isn't valid Java syntax
        // if (a > b) then c = 0; 

        // condition needs to be enclosed between parantheses
        // if a > b { c = 0; }

        // no issue
        // if (a > b) c = 0;

        // block needs to be enclosed before an else branch
        // if (a > b) c = 0 else b = 0;

        // double x = 0.7;
        // double y = 0.2;

        // if( (0 < x && x < 1) && (0 < y && y < 1)) {
        //     StdOut.print(true);
        // } else StdOut.print(false);

        // int f = 0; 
        // int g = 1;
        // for (int i = 0; i <= 15; i++) {
        //     StdOut.println(f);
        //     f = f + g;
        //     g = f - g;
        //     System.out.println(g);
        // }

        // double t = 9.0;
        // while (Math.abs(t - 9.0/t) > .001)
        //     t = (9.0/t + t) / 2.0;
        //     StdOut.println(t);
        // StdOut.printf("%.5f\n", t);


        // int sum = 0;
        // for (int i = 1; i < 1000; i++)
        //     for (int j = 0; j < i; j++)
        //         sum++;
        // StdOut.println(sum);

        // var N = 100;
        // int sum = 0; 
        // for (int i = 1; i < 1000; i *= 2)
        //     for (int j = 0; j < N; j++)
        //         sum++;
        // StdOut.println(sum); 

        // System.out.println('b');
        // System.out.println('b' + 'c');
        // System.out.println((char) ('a' + 4));

        // int N = 10;
        // String s = "";
        // for (int n = N; n > 0; n /= 2)
        //     s = (n % 2) + s;
        // StdOut.println(s);
        // boolean[][] a = new boolean[2][4];
        // boolean[][] a = {{true, false, true, true}, {true, true, false, false}};
        // // boolean[2][4] a = [[true, false, true ,true], [true, true, false, false]]; 

        // for(int i = 0; i < a.length; i++) {
        //     for(int j = 0; j < 4; j++) {
        //         if(a[i][j] == true) {
        //             StdOut.print('*');
        //         }
        //         else {
        //             StdOut.print(' ');
        //         }
        //     }
        //     StdOut.print('\n');
        // }

        // int[] a = new int[10];
        // for (int i = 0 ; i < 10; i++)
        //     a[i] = 9 - i;
        // for (int i = 0 ; i < a.length; i++)
        //     StdOut.print(a[i]);
        // for (int i = 0; i < 10; i++)
        //     a[i] = a[a[i]];
        // for (int i = 0; i < 10; i++)
        //     StdOut.print(a[i]);

        // int[][] a = {{1, 2, 3}, {1, 2, 3}};

        // for (int j = 0; j < 3; j++)
        // { 
        //     for (int i = 0; i < 2; i++)
        //     { 
        //         StdOut.print(a[i][j] + " ");
        //     }
        //     StdOut.println();
        // }
        
        // int[] a = {1, 1, 2, 3, 4, 5, 6};
        // int M = 7;
        // int[] result = new int[M];
        // result = histogram(a, M);
        // for(int i = 0; i < M; i++) {
        //     StdOut.println(result[i]);
        // }

        StdOut.println(exR1(6));
        
    }

}

/******************************************************************************
 *  Copyright 2002-2025, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/

