import java.util.Date;

public class Transaction {
    private double amount;
    private Date date;
    private String note;
    private Account inAccount;

    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.date = new Date();
        this.note = "";
    }

    public Transaction(double amount, Account inAccount, String note) {
        this(amount, inAccount);
        this.note = note;
    }
}
