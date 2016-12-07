package com.jkmalan.adoptafriend.listing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListingManager {

    private List<Listing> listingCache;

    public ListingManager() {
        listingCache = new ArrayList<>();
    }

    public void createListing(int owner, String title, String zip, String type, String age, String sex, String desc, File photo) {

    }

    public void modifyListing(int lid, String title, String zip, String type, String age, String sex, String desc, File photo) {

    }

    public void deleteListing(int lid) {

    }

}
