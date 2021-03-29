package payment;

import users.User;

public class Account {
    private User holder;
    private double balance;

    private Account() {

    }

    public void addAmount(double amount) {
        balance += amount;
    }
}
