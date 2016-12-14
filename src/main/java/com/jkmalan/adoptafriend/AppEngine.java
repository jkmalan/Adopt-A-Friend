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

    public static void enable() {
        if (ENGINE == null) {
            ENGINE = new AppEngine();
        }
    }

    public static void disable() {
        if (ENGINE != null) {
            getDatabaseManager().shutdown();
            getListingManager().shutdown();
            getUserManager().shutdown();
            ENGINE = null;
        }
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
