package bank;

import exceptions.NotEnoughMoneyException;

public class ATM {
    private double balance;

    public double takeCashAmount(double moneyAmount) {
        if (Double.compare(moneyAmount, this.balance) > 0) {
            throw new NotEnoughMoneyException(balance);
        }

        //FIXME implement
        return 0;
    }
}
