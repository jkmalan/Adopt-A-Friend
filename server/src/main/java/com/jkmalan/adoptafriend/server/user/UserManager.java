/*
* This file is part of Adopt-A-Friend, licensed under the MIT License (MIT).
*
* Copyright (c) Adopt-A-Friend
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/
package com.jkmalan.adoptafriend.server.user;

import com.jkmalan.adoptafriend.common.user.User;
import com.jkmalan.adoptafriend.server.AppEngine;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Creates, retrieves, validates, and modifies users
 */
public class UserManager {

    public UserManager() {

    }

    /**
     * Validates the credentials provided by the user
     *
     * @param username The username
     * @param password The password as a character array
     * @return If login is valid, return the unique internal id, else return -1
     */
    public int validateCredentials(String username, char[] password) {
        int result = -1;
        UserAccount user = (UserAccount) AppEngine.getDatabaseManager().selectUser(username);
        if (user != null) {
            byte[] salt = user.getSalt();
            String hash = user.getHash();
            String testHash = generateHash(password, salt);
            if (hash.equals(testHash)) {
                result = user.getUID();
            }
        }
        return result;
    }

    /**
     * Retrieve the User object for the specified unique internal id
     *
     * @param uid The user id
     * @return The User object
     */
    public User getUser(int uid) {
        return AppEngine.getDatabaseManager().selectUser(uid);
    }

    /**
     * Retrieve the User object for the specified unique username
     *
     * @param username The username
     * @return The User object
     */
    public User getUser(String username) {
        return AppEngine.getDatabaseManager().selectUser(username);
    }

    /**
     * Creates a new user using the specified information
     *
     * @param username  The unique username to use for logins
     * @param password  The password for the user
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The unique email to use for logins
     * @param phone     The phone number of the user
     * @param street    The street address of the user
     * @param city      The city of the user
     * @param state     The state of the user
     * @param zip       The zip code of the user
     */
    public void createUser(String username, char[] password, String firstName, String lastName,
                           String email, String phone, String street, String city, String state, String zip) {
        byte[] salt = generateSalt();
        String hash = generateHash(password, salt);
        AppEngine.getDatabaseManager().insertUser(username, salt, hash, firstName, lastName, email, phone, street, city, state, zip);
    }

    /**
     * Modifies a user with the specified information
     *
     * @param uid       The unique internal id for the user
     * @param username  The unique username to use for logins
     * @param password  The password for the user
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The unique email to use for logins
     * @param phone     The phone number of the user
     * @param street    The street address of the user
     * @param city      The city of the user
     * @param state     The state of the user
     * @param zip       The zip code of the user
     */
    public void modifyUser(int uid, String username, char[] password, String firstName, String lastName,
                           String email, String phone, String street, String city, String state, String zip) {
        byte[] salt = generateSalt();
        String hash = generateHash(password, salt);
        AppEngine.getDatabaseManager().updateUser(uid, username, salt, hash, firstName, lastName, email, phone, street, city, state, zip);
    }

    /**
     * Deletes a user with the specified unique internal id
     *
     * @param uid The unique internal id for the user
     */
    public void deleteUser(int uid) {
        AppEngine.getDatabaseManager().deleteUser(uid);
    }

    /*
     * Generates a random salt to be used for hashing purposes
     *
     * @return A random salt
     */
    private byte[] generateSalt() {
        SecureRandom rand = null;
        byte[] salt = new byte[16];
        try {
            rand = SecureRandom.getInstance("SHA1PRNG");
            rand.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }

    /*
     * Generates a password hash using the provided salt and message
     *
     * @param message A character array to hash
     * @param salt A salt for the hash
     * @return The salted hash
     */
    private String generateHash(char[] message, byte[] salt) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            ByteBuffer bb = Charset.forName("UTF-8").encode(CharBuffer.wrap(message));
            byte[] bytes = md.digest(Arrays.copyOfRange(bb.array(), bb.position(), bb.limit()));
            Arrays.fill(bb.array(), (byte) 0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Failure to find algorithm
        }
        return hash;
    }

}
