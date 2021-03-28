import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String userId;
    private byte[] pinHash;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public User(String firstName, String lastName, Bank bank, String pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = bank.getNewUserId();
        this.transactions = new ArrayList<Transaction>();
        this.accounts = new ArrayList<Account>();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            this.pinHash = md.digest(pin.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Новый пользователь " + firstName + " " + lastName + " c ID " + userId + " создан.");
    }

    public void addAccount(Account addAcct) {
        this.accounts.add(addAcct);
    }

    public String getUserId() {
        return this.userId;
    }

    public boolean validatePin(String userPin){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return MessageDigest.isEqual(md.digest(userPin.getBytes(StandardCharsets.UTF_8)), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
