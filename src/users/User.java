package users;

import money.Account;

public class User extends Person {

    private Account account;

    public User(String firstName, String lastName, String pin) {
        super(firstName, lastName, pin);
    }

    public void setAccount(Account someAccount) {
        this.account = someAccount;
    }

    public double getBalance() {
        return this.account.getBalance();
    }

    public Account getAccount() {
        return this.account;
    }
}
