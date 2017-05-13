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

/**
 * Represents a user account used for authentication
 */
public class UserAccount extends User {

    private byte[] salt;
    private String hash;

    public UserAccount(int uid, String username) {
        super(uid, username);
    }

    /**
     * Gets the salt value
     *
     * @return The salt value
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * Sets the salt value
     *
     * @param salt The salt value
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    /**
     * Gets the complete hash value
     *
     * @return The hash value
     */
    public String getHash() {
        return hash;
    }

    /**
     * Sets the complete hash value
     *
     * @param hash The hash value
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

}
