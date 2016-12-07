package com.jkmalan.adoptafriend;

import com.jkmalan.adoptafriend.database.DatabaseManager;
import com.jkmalan.adoptafriend.listing.ListingManager;
import com.jkmalan.adoptafriend.user.UserManager;

public class AppEngine {

    private static AppEngine ENGINE = null;

    private final DatabaseManager databaseManager;
    private final ListingManager listingManager;
    private final UserManager userManager;

    private AppEngine() {
        databaseManager = new DatabaseManager();
        listingManager = new ListingManager();
        userManager = new UserManager();
    }

    protected static void init() {
        ENGINE = new AppEngine();
    }

    public static DatabaseManager getDatabaseManager() {
        return ENGINE.databaseManager;
    }

    public static ListingManager getListingManager() {
        return ENGINE.listingManager;
    }

    public static UserManager getUserManager() {
        return ENGINE.userManager;
    }

}
