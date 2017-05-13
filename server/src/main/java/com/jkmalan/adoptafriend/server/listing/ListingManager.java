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
package com.jkmalan.adoptafriend.server.listing;

import com.jkmalan.adoptafriend.common.listing.Listing;
import com.jkmalan.adoptafriend.server.ServerEngine;

import java.util.List;

/**
 * Creates, retrieves, and modifies listings
 */
public class ListingManager {

    public ListingManager() {

    }

    /**
     * Retrieves a list of listings with the specified owner id
     *
     * @param owner The owner id
     * @return If listings are found, returns a List of Listing objects, or else it returns an empty List
     */
    public List<Listing> getListings(int owner) {
        return ServerEngine.getDatabaseManager().selectListings(owner);
    }

    /**
     * Retrieves a listing with the specified unique internal id
     *
     * @param lid The listing id
     * @return The Listing Object
     */
    public Listing getListing(int lid) {
        return ServerEngine.getDatabaseManager().selectListing(lid);
    }

    /**
     * Creates a new listing using the specified information
     *
     * @param owner The owner id of the listing
     * @param title The title of the listing
     * @param type The type of pet for the listing
     * @param sex The sex of pet for the listing
     * @param desc The description of the listing
     */
    public void createListing(int owner, String title, String type, String sex, String desc) {
        ServerEngine.getDatabaseManager().insertListing(owner, title, type, sex, desc);
    }

    /**
     * Modifies a listing with the specified information
     *
     * @param lid The unique internal id for the listing
     * @param title The title of the listing
     * @param type The type of pet for the listing
     * @param sex The sex of pet for the listing
     * @param desc The description of the listing
     */
    public void modifyListing(int lid, String title, String type, String sex, String desc) {
        ServerEngine.getDatabaseManager().updateListing(lid, title, type, sex, desc);
    }

    /**
     * Deletes a listing with the specified unique internal id
     *
     * @param lid The unique internal id for the listing
     */
    public void deleteListing(int lid) {
        ServerEngine.getDatabaseManager().deleteListing(lid);
    }

}
