package users;

import money.Account;

public class User extends Person {
    private String firstName;
    private String lastName;
    private long userID;
    private byte[] pinHash;
    private Account account;

    public User (String firstName, String lastName, String pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = makeNewID();
        this.pinHash = makeHash(pin);
    }

    public void setAccount (Account someAccount) {
        this.account = someAccount;
    }

    public double getBalance() {
        return this.account.getBalance();
    }

    public long getID() {
        return userID;
    }
}
