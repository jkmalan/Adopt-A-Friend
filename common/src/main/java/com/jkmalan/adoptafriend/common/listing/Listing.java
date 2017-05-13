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
package com.jkmalan.adoptafriend.common.listing;

/**
 * Represents a Listing for a pet
 */
public class Listing {

    private final int lid;
    private final int ownerid;

    private String title;
    private String type;
    private String sex;
    private String desc;

    public Listing(int lid, int ownerid) {
        this.lid = lid;
        this.ownerid = ownerid;
    }

    /**
     * Gets the listing id
     *
     * @return The unique internal id for the listing
     */
    public int getLID() {
        return lid;
    }

    /**
     * Gets the owner id
     *
     * @return The unique internal id for the owner
     */
    public int getOwnerID() {
        return ownerid;
    }

    /**
     * Gets the title of the listing
     *
     * @return The title of the listing
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the listing
     *
     * @param title The title of the listing
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the type of the pet
     *
     * @return The type of the pet
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the pet
     *
     * @param type The type of the pet
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the sex of the pet
     *
     * @return The sex of the pet
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the sex of the pet
     *
     * @param sex The sex of the pet
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets the description of the pet
     *
     * @return The description of the pet
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description of the pet
     *
     * @param desc The description of the pet
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
