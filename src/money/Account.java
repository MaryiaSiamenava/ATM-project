package money;

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

    public void addMoneyToAccount(double amount) {
        this.balance += amount;
    }

    public void takeMoneyFromAccount(double amount) {
        this.balance -= amount;
    }
}
