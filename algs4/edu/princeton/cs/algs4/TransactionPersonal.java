import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;


public class TransactionPersonal implements Comparable<TransactionPersonal> {
    private final String who;       // Customer or entity name
    private final Date when;        // Date of the transaction
    private final double amount;    // Financial value

    /**
     * Initializes a new transaction with explicit values.
    */
    public TransactionPersonal(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount cannot be NaN or infinite.");
        }
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    /**
     * Initializes a new transaction by parsing a single string in the format "who when amount".
     * Example input: "Turing 5/22/1939 11.99"
     */
    public TransactionPersonal(String transaction) {
        String[] fields = transaction.trim().split("\\s+");
        if (fields.length < 3) {
            throw new IllegalArgumentException("Invalid transaction string format");
        }
        this.who = fields[0];
        this.when = new Date(fields[1]);
        this.amount = Double.parseDouble(fields[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        }
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    @Override 
    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    /**
     * Follows the 5-step equality pattern from page 103.
     */
    @Override 
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;

        TransactionPersonal that = (TransactionPersonal) other;
        return (Double.compare(this.amount, that.amount) == 0)
                && this.who.equals(that.who)
                && this.when.equals(that.when);
    }

    

    @Override 
    public int compareTo(TransactionPersonal that) {
        return Double.compare(this.amount, that.amount);
    }

    @Override 
    public int hashCode() {
        int hash = 1; 
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + Double.hashCode(amount);
        return hash;
    }

    /**
     * Unit test client.
     */
    public static void main(String[] args) {
        Date date1 = new Date(5, 22, 1939);
        TransactionPersonal t1 = new TransactionPersonal("Turing", date1, 11.99);
        TransactionPersonal t2 = new TransactionPersonal("Turing 5/22/1939 11.99");
        TransactionPersonal t3 = new TransactionPersonal("Hopper 7/14/1945 25.50");

        StdOut.println("--- Transaction Strings ---");
        StdOut.println("t1: " + t1);
        StdOut.println("t2: " + t2);
        StdOut.println("t3: " + t3);

        StdOut.println("\n--- Equality Tests ---");
        StdOut.println("t1.equals(t2) [Expected: true]: " + t1.equals(t2));
        StdOut.println("t1.equals(t3) [Expected: false]: " + t1.equals(t3));

        StdOut.println("\n--- Comparison & Hash Tests ---");
        StdOut.println("t1.compareTo(t3) [Expected: -1]: " + t1.compareTo(t3));
        StdOut.println("t1.hashCode() == t2.hashCode(): " + (t1.hashCode() == t2.hashCode()));
        
    }
}