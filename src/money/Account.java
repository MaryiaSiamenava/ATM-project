package money;

//FIXME: for current version Account class seems unnecessary. Make it as balance directly on User class
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
        //FIXME : how you check that balance always > 0?
        this.balance -= amount;
    }
}
