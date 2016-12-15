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
