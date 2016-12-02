package com.jkmalan.adoptafriend.user;

public class Account {

    private final int uid;

    private String hash;

    public Account(int uid, String hash) {
        this.uid = uid;
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

}
