package atm;

import exceptions.NotEnoughMoneyException;

public class ATM {
    private double balance;

    public double getBalance() {
        return this.balance;
    }

    public double takeCashAmount(double moneyAmount) {
        if (Double.compare(moneyAmount, this.balance) > 0) {
            throw new NotEnoughMoneyException(balance);
        }

        this.balance -= moneyAmount;
        return moneyAmount;
    }

    public void putCashAmount(double moneyAmount) {
        this.balance += moneyAmount;
    }
}
