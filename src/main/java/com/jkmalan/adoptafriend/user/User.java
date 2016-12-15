package com.jkmalan.adoptafriend.user;

import java.io.File;

/**
 * Represents a User in the system
 */
public class User {

    // Holds the unique internal id for the user
    private final Integer uid;

    private String username;
    private byte[] salt;
    private String hash;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String desc;
    private File photo;

    /*
     * Constructs a User object
     *
     * @param uid The unique internal id for the user
     * @param username The unique username to use for logins
     */
    public User(int uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    /**
     * Gets the user id
     *
     * @return The unique internal id for the user
     */
    public Integer getUserID() {
        return uid;
    }

    /**
     * Gets the username
     *
     * @return The unique username to use for logins
     */
    public String getUserName() {
        return username;
    }

    /*
     * Sets a password salt for this user
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    /*
     * Gets the password salt for this user
     */
    protected byte[] getSalt() {
        return salt;
    }

    /*
     * Sets the password hash for this user
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /*
     * Gets the password hash for this user
     */
    protected String getHash() {
        return hash;
    }

    /**
     * Sets the first name of the user
     *
     * @param firstName The first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the first name of the user
     *
     * @return The first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the last name of the user
     *
     * @param lastName The last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the last name of the user
     *
     * @return The last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the email of the user
     *
     * @param email The email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the email of the user
     *
     * @return The email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the street address of the user
     *
     * @param street The street address of the user
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the street address of the user
     *
     * @return The street address of the user
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the city of the user
     *
     * @param city The city of the user
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the city of the user
     *
     * @return The city of the user
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the state of the user
     *
     * @param state The state of the user
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the state of the user
     *
     * @return The state of the user
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the zip code of the user
     *
     * @param zip The zip code of the user
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the zip code of the user
     *
     * @return The zip code of the user
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the phone number of the user
     *
     * @param phone The phone number of the user
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the phone number of the user
     *
     * @return The phone number of the user
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the description of the user
     *
     * @param desc The description of the user
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets the description of the user
     *
     * @return The description of the user
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the photo of the user
     *
     * @param photo The photo of the user
     */
    public void setPhoto(File photo) {
        this.photo = photo;
    }

    /**
     * Gets the photo of the user
     *
     * @return The photo of the user
     */
    public File getPhoto() {
        return photo;
    }

}

