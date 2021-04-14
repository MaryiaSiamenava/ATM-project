package users;

public class User extends Person {

    private double balance;

    public User(String firstName, String lastName, String pin) {
        super(firstName, lastName, pin);
    }

    public double getBalance() {
        return this.balance;
    }

    public void addMoneyToAccount(double amount) {
        this.balance += amount;
    }

    public void takeMoneyFromAccount(double amount) {
        if(Double.compare(this.balance, amount) > 0) {
            this.balance -= amount;
        } else {
            System.out.println("На счете недостаточно средств. Вы можете снять только " + this.balance);
        }
    }
}
