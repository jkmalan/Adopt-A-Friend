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
package com.jkmalan.adoptafriend;

import com.jkmalan.adoptafriend.database.DatabaseManager;
import com.jkmalan.adoptafriend.listing.ListingManager;
import com.jkmalan.adoptafriend.user.UserManager;

/**
 * Provides direct access to the DatabaseManager, ListingManager, and UserManager
 */
public class ServerEngine {

    // The singleton instance of ServerEngine
    private static ServerEngine ENGINE = null;

    private final DatabaseManager databaseManager;
    private final ListingManager listingManager;
    private final UserManager userManager;

    /*
     * Constructs the ServerEngine instance
     * Initializes all the managers
     */
    private ServerEngine() {
        databaseManager = new DatabaseManager();
        listingManager = new ListingManager();
        userManager = new UserManager();
    }

    /**
     * Initializes the ServerEngine backend
     */
    public static void enable() {
        if (ENGINE == null) {
            ENGINE = new ServerEngine();
        }
    }

    /**
     * Shuts down the ServerEngine backend
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
     * @return The ServerEngine DatabaseManager
     */
    public static DatabaseManager getDatabaseManager() {
        return ENGINE.databaseManager;
    }

    /**
     * Gets the main instance of the ListingManager
     *
     * @return The ServerEngine ListingManager
     */
    public static ListingManager getListingManager() {
        return ENGINE.listingManager;
    }

    /**
     * Gets the main instance of the UserManager
     *
     * @return The ServerEngine UserManager
     */
    public static UserManager getUserManager() {
        return ENGINE.userManager;
    }

}
