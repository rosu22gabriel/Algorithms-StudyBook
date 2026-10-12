package edu.princeton.cs.algs4;

public class Rational {
    private final long num;
    private final long den;

    /**
     * Initializes a new Rational number. Reduced to lowest terms.
     */

    public Rational(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }

        long g = gcd(numerator, denominator);
        long n = numerator / g;
        long d = denominator / g;

        // Ensure the denominator is always positive
        if (d < 0) {
            n = -n;
            d = -d;
        }

        this.num = n;
        this.den = d;
    }

    /**
     * Computes the greatest common divisor using Euclid's algorithm
     */
    private static long gcd(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }

    public long numerator() {
        return num;
    }

    public long denominator() {
        return den;
    }

    /**
     * Returns the sum of this rational number and b.
     */
    public Rational plus(Rational b) {
        return new Rational(this.num * b.den + b.num * this.den, this.den * b.den);
    }

    /**
     * Returns the difference of this rational number and b.
     */
    public Rational minus(Rational b) {
        return new Rational(this.num * b.den - b.num * this.den, this.den * b.den);
    }

    /**
     * Returns the produc of this rational number and b.
     */ 
    public Rational times(Rational b) {
        return new Rational(this.num * b.num, this.den * b.den);
    }

    /**
     *  Returns the quotient of this rational number and b.
     */
    public Rational divides(Rational b) {
        if (b.num == 0) {
            throw new ArithmeticException("Division by zero.");
        }
        return new Rational(this.num * b.den, this.den * b.num);
    }

    @Override 
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;
        Rational that = (Rational) other;
        return this.num == that.num && this.den == that.den;
    }

    @Override 
    public String toString() {
        if (den == 1) return String.valueOf(num);
        return num + "/" + den;
    }

    /**
     * Test client exercising all public methods
     */
    public static void main(String[] args) {
        Rational a = new Rational(1, 2);
        Rational b = new Rational(2, 3);
        Rational c = new Rational(2, 4); // Reduces to 1/2

        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("c = " + c + " (auto-reduced from 2/4)");

        // Test equality
        StdOut.println("a.equals(c): " + a.equals(c)); // true
        StdOut.println("a.equals(b): " + a.equals(b)); // false

        // Test arithmetic operations
        StdOut.println("a + b = " + a.plus(b));        // 7/6
        StdOut.println("a - b = " + a.minus(b));       // -1/6
        StdOut.println("a * b = " + a.times(b));       // 1/3
        StdOut.println("a / b = " + a.divides(b));     // 3/4

        // Edge case: whole number representation
        Rational d = new Rational(6, 3);
        StdOut.println("d = " + d + " (reduced from 6/3)");
    }   
}