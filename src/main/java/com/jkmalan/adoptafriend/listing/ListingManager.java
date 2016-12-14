package com.jkmalan.adoptafriend.listing;

import com.jkmalan.adoptafriend.AppEngine;

public class ListingManager {

    public ListingManager() {

    }

    public void shutdown() {

    }

    public void createListing(int owner, String title, String zip, String type, String sex, int age, String desc,
                              String photo) {
        AppEngine.getDatabaseManager().insertListing(owner, title, zip, type, sex, age, desc, photo);
    }

    public void modifyListing(int lid, String title, String zip, String type, String sex, int age, String desc,
                              String photo) {
        AppEngine.getDatabaseManager().updateListing(lid, title, zip, type, sex, age, desc, photo);
    }

    public void deleteListing(int lid) {
        AppEngine.getDatabaseManager().deleteListing(lid);
    }

}
