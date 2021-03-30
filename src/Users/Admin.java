package Users;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Admin extends Person {
    private String firstName;
    private String lastName;
    private long adminID;
    private byte[] passwordHash;

    public Admin (String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adminID = makeNewID();
        this.passwordHash = makeHash(password);
    }

}
