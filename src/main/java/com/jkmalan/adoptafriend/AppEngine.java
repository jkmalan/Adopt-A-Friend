package com.jkmalan.adoptafriend;

import com.jkmalan.adoptafriend.database.DatabaseManager;
import com.jkmalan.adoptafriend.listing.ListingManager;
import com.jkmalan.adoptafriend.user.UserManager;

/**
 * Provides direct access to the DatabaseManager, ListingManager, and UserManager
 */
public class AppEngine {

    // The singleton instance of AppEngine
    private static AppEngine ENGINE = null;

    private final DatabaseManager databaseManager;
    private final ListingManager listingManager;
    private final UserManager userManager;

    /*
     * Constructs the AppEngine instance
     * Initializes all the managers
     */
    private AppEngine() {
        databaseManager = new DatabaseManager();
        listingManager = new ListingManager();
        userManager = new UserManager();
    }

    /**
     * Initializes the AppEngine backend
     */
    public static void enable() {
        if (ENGINE == null) {
            ENGINE = new AppEngine();
        }
    }

    /**
     * Shuts down the AppEngine backend
     * Individually disables the managers
     */
    public static void disable() {
        if (ENGINE != null) {
            getDatabaseManager().shutdown();
            getListingManager().shutdown();
            getUserManager().shutdown();
            ENGINE = null;
        }
    }

    /**
     * Gets the main instance of the DatabaseManager
     *
     * @return The AppEngine DatabaseManager
     */
    public static DatabaseManager getDatabaseManager() {
        return ENGINE.databaseManager;
    }

    /**
     * Gets the main instance of the ListingManager
     *
     * @return The AppEngine ListingManager
     */
    public static ListingManager getListingManager() {
        return ENGINE.listingManager;
    }

    /**
     * Gets the main instance of the UserManager
     *
     * @return The AppEngine UserManager
     */
    public static UserManager getUserManager() {
        return ENGINE.userManager;
    }

}
