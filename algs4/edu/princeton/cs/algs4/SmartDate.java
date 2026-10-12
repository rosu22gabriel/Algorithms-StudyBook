package edu.princeton.cs.algs4;

public class SmartDate {
    // Number of days per month for a non-leap year (1-indexed for convenience)
    private static final int[] DAYS = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final int month;
    private final int day;
    private final int year;

    /**
     * Constructs a SmartDate object.
     * Throws an IllegalArgumentException if the date parameters do not represent a valid calendar date.
     */
    public SmartDate(int month, int day, int year) {
        if (!isValid(month, day, year)) {
            throw new IllegalArgumentException("Invalid date: " + month + "/" + day + "/" + year);
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Determines wether a given year is a leap year in the Gregorian calendar.
     */
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    /**
     * Validates if month, day, and year from a legitimate date.
     */
    private static boolean isValid(int m, int d, int y) {
        if (y <= 0) return false;
        if (m < 1 || m > 12) return false;
        if (d < 1) return false;

        int maxDays = DAYS[m];
        if (m == 2 && isLeapYear(y)) {
            maxDays = 29;
        }

        return d <= maxDays;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    @Override 
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    @Override 
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        SmartDate that = (SmartDate) other;
        return this.month == that.month && this.day == that.day && this.year == that.year;
    }

    public static void main(String[] args) {
        // Test valid dates
        SmartDate d1 = new SmartDate(2, 29, 2024); // Leap year
        StdOut.println("Successfully created: " + d1);
        
        SmartDate d2 = new SmartDate(10, 9, 2026);
        StdOut.println("Successfully created: " + d2);

        // Test invalid date (Non-leap year February 29)
        try {
            SmartDate invalidDate = new SmartDate(2, 29, 2025);
            StdOut.println("Created: " + invalidDate);
        } catch (IllegalArgumentException e) {
            StdOut.println("Caught expected error: " + e.getMessage());
        }

        // Test invalid month
        try {
            SmartDate invalidDate = new SmartDate(2, 29, 2025);
            StdOut.println("Created: " + invalidDate);
        } catch (IllegalArgumentException e) {
            StdOut.println("Caught expected error: " + e.getMessage());
        }
    }
}