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
package com.jkmalan.adoptafriend.listing;

import com.jkmalan.adoptafriend.AppEngine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListingManager {

    private final Map<Integer, Listing> listingCache = new HashMap<>();

    public ListingManager() {

    }

    public void shutdown() {
        listingCache.clear();
    }

    public List<Listing> getListings(int owner) {
        List<Listing> listings = AppEngine.getDatabaseManager().selectListings(owner);
        for (Listing l : listings) {
            listingCache.put(l.getListingID(), l);
        }
        return listings;
    }

    public List<Listing> getListings(String title, String zip, String type, String sex, int age) {
        List<Listing> listings = AppEngine.getDatabaseManager().selectListings(title, zip, type, sex, age);
        for (Listing l : listings) {
            listingCache.put(l.getListingID(), l);
        }
        return listings;
    }

    public Listing getListing(int lid) {
        Listing listing = null;
        if (listingCache.containsKey(lid)) {
            listing = listingCache.get(lid);
        } else {
            listing = AppEngine.getDatabaseManager().selectListing(lid);
            listingCache.put(listing.getListingID(), listing);
        }
        return listing;
    }

    public void createListing(int owner, String title, String zip, String type, String sex, int age, String desc,
                              File photo) {
        AppEngine.getDatabaseManager().insertListing(owner, title, zip, type, sex, age, desc, photo);
    }

    public void modifyListing(int lid, String title, String zip, String type, String sex, int age, String desc,
                              File photo) {
        AppEngine.getDatabaseManager().updateListing(lid, title, zip, type, sex, age, desc, photo);
        Listing listing = AppEngine.getDatabaseManager().selectListing(lid);
        listingCache.put(listing.getListingID(), listing);
    }

    public void deleteListing(int lid) {
        AppEngine.getDatabaseManager().deleteListing(lid);
        listingCache.remove(lid);
    }

}
