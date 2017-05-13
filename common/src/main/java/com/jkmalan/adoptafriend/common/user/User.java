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
package com.jkmalan.adoptafriend.common.user;

/**
 * @author jkmalan (aka John Malandrakis)
 */
public class User {

    private final int uid;
    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zip;

    public User(int uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    /**
     * Gets the user id
     *
     * @return The unique internal id for the user
     */
    public int getUID() {
        return uid;
    }

    /**
     * Gets the username
     *
     * @return The unique username to use for logins
     */
    public String getUsername() {
        return username;
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
     * Sets the first name of the user
     *
     * @param firstName The first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * Sets the last name of the user
     *
     * @param lastName The last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Sets the email of the user
     *
     * @param email The email of the user
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Sets the phone number of the user
     *
     * @param phone The phone number of the user
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * Sets the street address of the user
     *
     * @param street The street address of the user
     */
    public void setStreet(String street) {
        this.street = street;
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
     * Sets the city of the user
     *
     * @param city The city of the user
     */
    public void setCity(String city) {
        this.city = city;
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
     * Sets the state of the user
     *
     * @param state The state of the user
     */
    public void setState(String state) {
        this.state = state;
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
     * Sets the zip code of the user
     *
     * @param zip The zip code of the user
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
}
