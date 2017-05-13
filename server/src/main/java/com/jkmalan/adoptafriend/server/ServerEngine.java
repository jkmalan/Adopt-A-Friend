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
package com.jkmalan.adoptafriend.server;

import com.jkmalan.adoptafriend.server.database.DatabaseManager;
import com.jkmalan.adoptafriend.server.listing.ListingManager;
import com.jkmalan.adoptafriend.server.net.ConnectionManager;
import com.jkmalan.adoptafriend.server.user.UserManager;

/**
 * Provides direct access to the DatabaseManager, ListingManager, and UserManager
 */
public class ServerEngine {

    // The singleton instance of ServerEngine
    private static ServerEngine ENGINE = null;

    private final DatabaseManager databaseManager;
    private final ListingManager listingManager;
    private final UserManager userManager;
    private final ConnectionManager connectionManager;

    /*
     * Constructs the ServerEngine instance
     * Initializes all the managers
     */
    private ServerEngine() {
        databaseManager = new DatabaseManager();
        listingManager = new ListingManager();
        userManager = new UserManager();
        connectionManager = new ConnectionManager();
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
     * Gets the main instance of the DatabaseManager
     *
     * This method is synchronized to prevent concurrent access
     * of the database by multiple clients at once. This is only
     * possible so long as all other methods funnel back through
     * this command for database access.
     *
     * @return The ServerEngine DatabaseManager
     */
    public static synchronized DatabaseManager getDatabaseManager() {
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

    /**
     * Gets the main instance of the ConnectionManager
     *
     * @return The ServerEngine ConnectionManager
     */
    public static ConnectionManager getConnectionManager() {
        return ENGINE.connectionManager;
    }

}
