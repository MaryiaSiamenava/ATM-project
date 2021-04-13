package com.maryiasiamenava.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static byte[] makeHash(String stringToHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error, NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
