package users;

import money.Account;

public class Admin extends Person {
    public Admin(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }

    @Override
    public Account getAccount() {
        return null;
    }

}
