package com.maryia.users;

import com.maryia.atm.ATM;
import com.maryia.exceptions.NotEnoughMoneyException;

import java.security.MessageDigest;

import static com.maryia.utils.HashUtils.makeHash;

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
        this.accessCodeHash = makeHash(accessCode);
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


    public boolean validateAccessCode(String inputAccessCode) {
        return MessageDigest.isEqual(makeHash(inputAccessCode), this.accessCodeHash);
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
