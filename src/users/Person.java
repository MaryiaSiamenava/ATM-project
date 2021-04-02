package users;

import atm.ATM;
import exceptions.NotEnoughMoneyException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Person {
    private static long uniqueID = 1;
    private byte[] AccessCode;

    protected long makeNewID() {
        this.uniqueID++;
        return uniqueID;
    }

    abstract long getID();

    protected byte[] makeHash(String AccessCodeString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            this.AccessCode = md.digest(AccessCodeString.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return this.AccessCode;
    }

    public boolean validateAccessCode(String inputAccessCode) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return MessageDigest.isEqual(md.digest(inputAccessCode.getBytes(StandardCharsets.UTF_8)), this.AccessCode);
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
