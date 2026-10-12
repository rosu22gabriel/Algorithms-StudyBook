package edu.princeton.cs.algs4;

public class RobustRational {
    private final long num;
    private final long den;

    /**
     * Initializes a new Rational number. Reduced to lowest terms.
     */
    public RobustRational(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }

        long g = gcd(numerator, denominator);
        long n = numerator / g;
        long d = denominator / g;

        if (d < 0) {
            n = -n;
            d = -d;
        }

        this.num = n;
        this.den = d;
    }
    
    private static long gcd(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;

        if (0 == n) return m;
        else return gcd(n, m % n);
    }

    // Helper methods with assertion checks for arithmetic overflow
    private static long safeAdd(long x, long y) {
        if (y > 0) {
            assert x <= Long.MAX_VALUE - y: "Overflow in addition: " + x + " + " + y;
        } else {
            assert x >= Long.MIN_VALUE - y: "Overflow in addition: " + x + " + " + y;
        }
        return x + y;
    }

    private static long safeSubtract(long x, long y) {
        if (y > 0) {
            assert x >= Long.MIN_VALUE + y : "Overflow in subtraction: " + x + " - " + y;
        } else {
            assert x <= Long.MAX_VALUE + y : "Overflow in subtraction: " + x + " - " + y;
        }
        return x - y;
    }

    private static long safeMultiply(long x, long y) { 
        if (x > 0) {
            if (y > 0) {
                assert x <= Long.MAX_VALUE / y : "Overflow in multiplication: " + x + " * " + y;
            } else if (y < 0) { 
                assert y >= Long.MIN_VALUE / x : "Overflow in multiplication: " + x + " * " + y;
            }
        } else if (x < 0) { 
            if (y > 0) { 
                assert x >= Long.MIN_VALUE / y : "Overflow in multiplication: " + x + " * " + y;
            } else if (y < 0) {
                assert x >= Long.MAX_VALUE / y : "Overflow in multiplication: " + x + " * " + y;
            }
        }
        return x * y;
    }

    public long numerator() {
        return num;
    }

    public long denominator() {
        return den;
    }

    public RobustRational plus(RobustRational b) { 
        long term1 = safeMultiply(this.num, b.den);
        long term2 = safeMultiply(b.num, this.den);
        long newNum = safeAdd(term1, term2);
        long newDen = safeMultiply(this.den, b.den);
        return new RobustRational(newNum, newDen);
    }

    public RobustRational minus(RobustRational b) { 
        long term1 = safeMultiply(this.num, b.den);
        long term2 = safeMultiply(b.num, this.den);
        long newNum = safeSubtract(term1, term2);
        long newDen = safeMultiply(this.den, b.den);
        return new RobustRational(newNum, newDen);
    }

    public RobustRational times(RobustRational b) {
        long newNum = safeMultiply(this.num, b.num);
        long newDen = safeMultiply(this.den, b.den);
        return new RobustRational(newNum, newDen);
    }

    public RobustRational divides(RobustRational b) {
        if (b.num == 0) {
            throw new ArithmeticException("Division by zero.");
        }
        long newNum = safeMultiply(this.num, b.den);
        long newDen = safeMultiply(this.den, b.num);
        return new RobustRational(newNum, newDen);
    }

    @Override 
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;
        RobustRational that = (RobustRational) other;
        return this.num == that.num && this.den == that.den;
    }

    @Override 
    public String toString() {
        if (den == 1) return String.valueOf(num);
        return num + "/" + den;
    }

    /**
     * Test client exercising valid operations and overflow detection.
     */
    public static void main(String[] args) {
        // Standard arithmetic operations
        RobustRational a = new RobustRational(1, 2);
        RobustRational b = new RobustRational(2, 3);

        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("a + b = " + a.plus(b));  // 7/6
        StdOut.println("a - b = " + a.minus(b)); // -1/6
        StdOut.println("a * b = " + a.times(b)); // 1/3
        StdOut.println("a / b = " + a.divides(b)); // 3/4

        // Large non-overflowing arithmetic 
        RobustRational large1 = new RobustRational(Long.MAX_VALUE / 2, 1);
        RobustRational large2 = new RobustRational(2, 1);

        StdOut.println("large1 * large2 = " + large1.times(large2));

        // Overflow assertion check
        StdOut.println("\n Testing overflow protection (requires -ea JVM flag):");
        try {
            RobustRational overflow1 = new RobustRational(Long.MAX_VALUE, 1);
            RobustRational overflow2 = new RobustRational(2, 1);
            RobustRational result = overflow1.times(overflow2);
            StdOut.println("Result (assertions disabled): " + result);
        } catch (AssertionError e) {
            StdOut.println("Caught assertion error successfully: " + e.getMessage());
        }
    }
} 