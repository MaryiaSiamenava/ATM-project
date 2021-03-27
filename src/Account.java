import java.util.ArrayList;

public class Account {
    private String name;
    private User holder;
    private ArrayList<Transaction> transactions;
    private double balance;

    public Account(String name, User holder, Bank bank) {
        this.name = name;
        holder.addAccount(this);
        bank.addAccount(this);
    }
}
