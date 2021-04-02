package users;

import atm.ATM;
import exceptions.NotEnoughMoneyException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Person {
    private static long uniqueID = 1;

    private long id;
    private String firstName;
    private String lastName;
    private byte[] accessCodeHash;

    public Person(String firstName, String lastName, String accessCode) {
        this.id = makeNewID();
        this.firstName = firstName;
        this.lastName = lastName;
        accessCodeHash = makeHash(accessCode);
    }

    protected long makeNewID() {
        return uniqueID++;
    }

    public long getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    protected byte[] makeHash(String AccessCodeString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            this.accessCodeHash = md.digest(AccessCodeString.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return this.accessCodeHash;
    }

    public boolean validateAccessCode(String inputAccessCode) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return MessageDigest.isEqual(md.digest(inputAccessCode.getBytes(StandardCharsets.UTF_8)), this.accessCodeHash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public double takeMoneyFromATM(ATM atm, double amount) {
        try {
            return atm.takeCashAmount(amount);
        } catch (NotEnoughMoneyException ex) {
            System.out.println("Not enough money. You can only take " + ex.getAtmBalance());
            throw ex;
        }
    }
}
