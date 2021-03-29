package users;

import payment.Account;

public class User extends Person {
    private String pinCode;
    private Account account;

    public User(Account account) {
        this.account = account;
    }

    public void putMoney(double amount) {
        account.addAmount(amount);
    }
}
