import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String userId;
    private byte[] pinHash;
    private Account account;
    private ArrayList<Transaction> transactions;

    public User(String firstName, String lastName, Bank bank, String pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = bank.getNewUserId();
        this.account = new Account();
        this.transactions = new ArrayList<Transaction>();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            this.pinHash = md.digest(pin.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
