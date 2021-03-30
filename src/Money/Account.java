package Money;

public class Account {
    private static long numberCounter = 0;
    private long number;
    private double balance;

    public long getNumber() {
        return this.number = numberCounter++;
    }

    public double getBalance() {
        return this.balance;
    }
}
