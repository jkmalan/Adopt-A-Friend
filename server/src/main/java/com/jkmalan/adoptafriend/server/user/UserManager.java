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

import com.jkmalan.adoptafriend.server.AppEngine;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<Integer, User> userCache = new HashMap<>();

    public UserManager() {

    }

    public void shutdown() {
        userCache.clear();
    }

    public int validateUser(String username, char[] password) {
        int result = -1;
        User user = AppEngine.getDatabaseManager().selectUser(username);
        if (user != null) {
            byte[] salt = user.getSalt();
            String hash = user.getHash();
            String testHash = generateHash(password, salt);
            if (hash.equals(testHash)) {
                result = user.getUserID();
            }
        }
        return result;
    }

    public User getUser(int uid) {
        User user = null;
        if (userCache.containsKey(uid)) {
            user = userCache.get(uid);
        } else {
            user = AppEngine.getDatabaseManager().selectUser(uid);
            userCache.put(user.getUserID(), user);
        }
        return user;
    }

    public void createUser(String username, char[] password, String firstName, String lastName,
                           String email, String phone, String street, String city, String state, String zip,
                           String desc, File photo) {
        byte[] salt = generateSalt();
        String hash = generateHash(password, salt);
        AppEngine.getDatabaseManager().insertUser(username, salt, hash, firstName, lastName, email, phone, street, city, state, zip, desc, photo);
        User user = AppEngine.getDatabaseManager().selectUser(username);
        userCache.put(user.getUserID(), user);
    }

    public void modifyUser(int uid, String username, char[] password, String firstName, String lastName,
                           String email, String phone, String street, String city, String state, String zip,
                           String desc, File photo) {
        byte[] salt = generateSalt();
        String hash = generateHash(password, salt);
        AppEngine.getDatabaseManager().updateUser(uid, username, salt, hash, firstName, lastName, email, phone, street, city, state, zip, desc, photo);
        User user = AppEngine.getDatabaseManager().selectUser(username);
        userCache.put(user.getUserID(), user);
    }

    public void deleteUser(int uid) {
        AppEngine.getDatabaseManager().deleteUser(uid);
        userCache.remove(uid);
    }

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
            // TODO Handle errors
        }
        return hash;
    }

}
