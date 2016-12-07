package com.jkmalan.adoptafriend.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Account {

    private final int uid;

    private String hash;
    private byte[] salt;

    public Account(int uid, byte[] salt, String hash) {
        this.uid = uid;
        this.salt = salt;
        this.hash = hash;
    }

    public int getUID() {
        return uid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    private void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String createHash(String message) {
        String hash = null;
        try {
            if (salt == null) {
                setSalt(createSalt());
            }

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(message.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Handle errors
        }
        return hash;
    }

    private byte[] createSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

}
