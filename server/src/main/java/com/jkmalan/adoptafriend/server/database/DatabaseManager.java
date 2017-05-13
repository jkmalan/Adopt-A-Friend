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
package com.jkmalan.adoptafriend.server.database;

import com.jkmalan.adoptafriend.common.listing.Listing;
import com.jkmalan.adoptafriend.common.user.User;
import com.jkmalan.adoptafriend.server.user.UserAccount;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates, retrieves and modifies data in the Database
 */
public class DatabaseManager {

    // Holds the SQLite Database object
    private final Database database;

    public DatabaseManager() {
        database = new Database(new File(".\\adoptafriend.db"));
        try {
            database.connect();
            createTables();
        } catch (SQLException e) {
            // TODO Failure to connect database
        }
    }

    /*
     * Creates the preliminary tables for the Adopt-A-Friend database
     * If a table already exists, it will avoid creating a table
     *
     * @throws SQLException
     */
    private void createTables() throws SQLException {
        String USER_TABLE = "CREATE TABLE IF NOT EXISTS user ( "
                + "uid INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username CHAR(20) NOT NULL,"
                + "passSalt BLOB NOT NULL,"
                + "passHash CHAR(40) NOT NULL,"
                + "firstName CHAR(24) NOT NULL,"
                + "lastName CHAR(24) NOT NULL,"
                + "email CHAR(60) NOT NULL,"
                + "street CHAR(45) NOT NULL,"
                + "city CHAR(24) NOT NULL,"
                + "state CHAR(2) NOT NULL,"
                + "zip CHAR(10) NOT NULL,"
                + "phone CHAR(10) NOT NULL,"
                + "UNIQUE (username),"
                + "UNIQUE (email)"
                + ");";
        String LISTING_TABLE = "CREATE TABLE IF NOT EXISTS listing ( "
                + "lid INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "owner INTEGER NOT NULL,"
                + "title CHAR(40) NOT NULL, "
                + "type CHAR(40) NOT NULL, "
                + "sex CHAR(1) NOT NULL, "
                + "desc CHAR(256),"
                + "FOREIGN KEY (owner) REFERENCES user (uid)"
                + "); ";
        database.executeStatement(USER_TABLE);
        database.executeStatement(LISTING_TABLE);
    }

    /**
     * Inserts a new Listing record into the Database
     *
     * @param owner The user id for the listing owner
     * @param title The title of the listing
     * @param type  The type of animal in the listing
     * @param sex   The sex of the animal in the listing
     * @param desc  The description of the listing
     */
    public void insertListing(int owner, String title, String type, String sex, String desc) {
        String query = "INSERT INTO listing (owner, title, type, sex, desc) "
                + "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setInt(1, owner);
            ps.setString(2, title);
            ps.setString(3, type);
            ps.setString(4, sex);
            ps.setString(5, desc);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Failure to insert listing
        } finally {
            database.closeStatement(ps);
        }
    }

    /**
     * Inserts a new User record into the Database
     *
     * @param username  The unique username to use for logins
     * @param salt      The unique salt to be used in password hashing
     * @param hash      The hashed version of the password
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The unique email to use for logins
     * @param phone     The phone number of the user
     * @param street    The street address of the user
     * @param city      The city of the user
     * @param state     The state of the user
     * @param zip       The zip code of the user
     */
    public void insertUser(String username, byte[] salt, String hash, String firstName, String lastName, String email, String phone, String street, String city, String state, String zip) {
        String query = "INSERT INTO user (username, passSalt, passHash, firstName, lastName, email, phone, street, city, state, zip) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, username);
            ps.setBytes(2, salt);
            ps.setString(3, hash);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setString(6, email);
            ps.setString(7, phone);
            ps.setString(8, street);
            ps.setString(9, city);
            ps.setString(10, state);
            ps.setString(11, zip);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Failure to insert user
        } finally {
            database.closeStatement(ps);
        }
    }

    /**
     * Updates an existing Listing record in the Database
     *
     * @param lid   The unique internal id for the listing
     * @param title The title of the listing
     * @param type  The type of animal in the listing
     * @param sex   The sex of the animal in the listing
     * @param desc  The description of the listing
     */
    public void updateListing(int lid, String title, String type, String sex, String desc) {
        String query = "UPDATE listing SET title = ?, type = ?, sex = ?, desc = ? "
                + "WHERE lid = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, title);
            ps.setString(2, type);
            ps.setString(3, sex);
            ps.setString(4, desc);
            ps.setInt(5, lid);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Failure to update listing
        } finally {
            database.closeStatement(ps);
        }

    }

    /**
     * Updates an existing User record in the Database
     *
     * @param uid       The unique internal id for the user
     * @param username  The unique username to use for logins
     * @param salt      The unique salt to be used in password hashing
     * @param hash      The hashed version of the password
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The unique email to use for logins
     * @param phone     The phone number of the user
     * @param street    The street address of the user
     * @param city      The city of the user
     * @param state     The state of the user
     * @param zip       The zip code of the user
     */
    public void updateUser(int uid, String username, byte[] salt, String hash, String firstName, String lastName, String email, String phone, String street, String city, String state, String zip) {
        String query = "UPDATE user SET username = ?, passSalt = ?, passHash = ?, firstName = ?, lastName = ?, email = ?, "
                + "phone = ?, street = ?, city = ?, state = ?, zip = ? "
                + "WHERE uid = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, username);
            ps.setBytes(2, salt);
            ps.setString(3, hash);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setString(6, email);
            ps.setString(7, phone);
            ps.setString(8, street);
            ps.setString(9, city);
            ps.setString(10, state);
            ps.setString(11, zip);
            ps.setInt(12, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Failure to update user
        } finally {
            database.closeStatement(ps);
        }

    }

    /**
     * Deletes an existing Listing record from the Database
     *
     * @param lid The unique internal id for the listing
     */
    public void deleteListing(int lid) {
        String query = "DELETE FROM listing WHERE lid = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setInt(1, lid);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Failure to delete listing
        } finally {
            database.closeStatement(ps);
        }
    }

    /**
     * Deletes an existing User record from the Database
     *
     * @param uid The unique internal id for the user
     */
    public void deleteUser(int uid) {
        String query = "DELETE FROM user WHERE uid = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setInt(1, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Failure to delete user
        } finally {
            database.closeStatement(ps);
        }
    }

    /**
     * Selects a User from the Database with the specified username
     *
     * @param username The unique username for user logins
     * @return If a record is found, returns a User object, or else it returns null
     */
    public User selectUser(String username) {
        String query = "SELECT * FROM user WHERE username = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int uid = rs.getInt("uid");
                String uname = rs.getString("username");
                UserAccount user = new UserAccount(uid, uname);

                byte[] salt = rs.getBytes("passSalt");
                String hash = rs.getString("passHash");
                user.setSalt(salt);
                user.setHash(hash);

                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String phone = rs.getString("phone");

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setStreet(street);
                user.setCity(city);
                user.setState(state);
                user.setZip(zip);
                user.setPhone(phone);

                return user;
            }
        } catch (SQLException e) {
            // TODO Failure to send query
        } finally {
            database.closeStatement(ps);
        }
        return null;

    }

    /**
     * Selects a User from the Database with the specified unique internal id
     *
     * @param uid The unique internal id for the user
     * @return If a record is found, returns a User object, or else it returns null
     */
    public User selectUser(int uid) {
        String query = "SELECT * FROM user WHERE uid = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("uid");
                String uname = rs.getString("username");
                UserAccount user = new UserAccount(id, uname);

                byte[] salt = rs.getBytes("passSalt");
                String hash = rs.getString("passHash");
                user.setSalt(salt);
                user.setHash(hash);

                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String phone = rs.getString("phone");

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setStreet(street);
                user.setCity(city);
                user.setState(state);
                user.setZip(zip);
                user.setPhone(phone);

                return user;
            }
        } catch (SQLException e) {
            // TODO Failure to send query
        } finally {
            database.closeStatement(ps);
        }
        return null;

    }

    /**
     * Selects a Listing from the Database with the specified unique internal id
     *
     * @param lid The unique internal id for the listing
     * @return If a record is found, returns a Listing object, or else it returns null
     */
    public Listing selectListing(int lid) {
        String query = "SELECT * FROM listing WHERE lid = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setInt(1, lid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("lid");
                int owner = rs.getInt("owner");
                Listing listing = new Listing(id, owner);

                String title = rs.getString("title");
                String type = rs.getString("type");
                String sex = rs.getString("sex");
                String desc = rs.getString("desc");

                listing.setTitle(title);
                listing.setType(type);
                listing.setSex(sex);
                listing.setDesc(desc);

                return listing;
            }
        } catch (SQLException e) {
            // TODO Failure to send query
        } finally {
            database.closeStatement(ps);
        }
        return null;
    }

    /**
     * Selects a series of Listings from the database with the specified internal owner id
     *
     * @param owner The internal owner id
     * @return If records are found, returns a List containing the Listings, else returns an empty List
     */
    public List<Listing> selectListings(int owner) {
        List<Listing> listings = new ArrayList<>();
        String query = "SELECT * FROM listing "
                + "WHERE owner = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setInt(1, owner);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int lid = rs.getInt("lid");
                int uid = rs.getInt("owner");
                Listing listing = new Listing(lid, uid);

                listing.setTitle(rs.getString("title"));
                listing.setType(rs.getString("type"));
                listing.setSex(rs.getString("sex"));
                listing.setDesc(rs.getString("desc"));

                listings.add(listing);
            }
        } catch (SQLException e) {
            // TODO Failure to send query
        } finally {
            database.closeStatement(ps);
        }
        return listings;
    }

}
