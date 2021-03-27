import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Admin {
    private String firstName;
    private String lastName;
    private String adminId;
    private byte[] passwordHash;

    public Admin(String firstName, String lastName, Bank bank, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adminId = bank.getNewAdminId();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            this.passwordHash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }


    }

    public String getAdminId() {
        return this.adminId;
    }
}
