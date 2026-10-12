package edu.princeton.cs.algs4;


public class ParsingDataTypes {
    public static class Date {
        private final int month;
        private final int day;
        private final int year;

        public Date(int month, int day, int year) {
            this.month = month;
            this.day = day;
            this.year = year;
        }

        /**
         * String parsing constructor for Date (Exercise 1.2.19).
         * Format: "month/day/year" (e.g., "5/22/1939")
         */
        public Date(String date) {
            String[] fields = date.trim().split("/");
            if (fields.length != 3) {
                throw new IllegalArgumentException("Invalid date format: " + date);
            }
            this.month = Integer.parseInt(fields[0]);
            this.day = Integer.parseInt(fields[1]);
            this.year = Integer.parseInt(fields[2]);
        }

        public int month() { return month; }
        public int day()   { return day;   }
        public int year()  { return year;  }

        @Override 
        public String toString() {
            return month + "/" + day + "/" + year;
        }

        @Override 
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Date that = (Date) other;
            return this.month == that.month && this.day == that.day && this.year == that.year;
        }
    }


    public static class Transaction {
        private final String who;
        private final Date when;
        private final double amount;

        public Transaction(String who, Date when, double amount) {
            this.who = who;
            this.when = when;
            this.amount = amount;
        }

        /**
         * String parsing constructor for Transaction (Exercise 1.2.19).
         * Format: "customer date amount" (e.g., "Turing 5/22/1939 11.99")
         */
        public Transaction(String transaction) {
            String[] fields = transaction.trim().split("\\s+");
            if (fields.length != 3) {
                throw new IllegalArgumentException("Invalid transaction format: " + transaction);
            }
            this.who = fields[0];
            this.when = new Date(fields[1]); // Reuses Date string parsing constructor
            this.amount = Double.parseDouble(fields[2]);
        }

        public String who() { return who; }
        public Date when() { return when; }
        public double amount() { return amount; }

        @Override 
        public String toString() {
            return String.format("%s %s %.2f", who, when, amount);
        }

        @Override 
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Transaction that = (Transaction) other;
            return Double.compare(that.amount, this.amount) == 0 &&
                this.who.equals(that.who) &&
                this.when.equals(that.when);
        }
    }

    /**
     * Test client verifying parsing constructors and Object equality.
     */
    public static void main(String[] args) {
        // Test Date parsing constructor
        String dateStr = "5/22/1939";
        Date date = new Date(dateStr);
        StdOut.println("Parsed Date: " + date);
        StdOut.println("Month: " + date.month() + ", Day: " + date.day() +", Year: " + date.year());

        // Test Transaction parsing constructor
        String transactionStr = "Turing 5/22/1939 11.99";
        Transaction transaction = new Transaction(transactionStr);
        StdOut.println("\nParsed Transaction: " + transaction);
        StdOut.println("Who: " + transaction.who());
        StdOut.println("When: " + transaction.when());
        StdOut.println("Amount: " + transaction.amount());

        // Validate consistency against standard constructors
        Date expectedDate = new Date(5, 22, 1939);
        Transaction expectedTransaction = new Transaction("Turing", expectedDate, 11.99);

        StdOut.println("\nDate match check: " + date.equals(expectedDate));
        StdOut.println("Transaction match check: " + transaction.equals(expectedTransaction));
    }
}