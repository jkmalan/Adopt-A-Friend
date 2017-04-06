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

import com.jkmalan.adoptafriend.server.listing.Listing;
import com.jkmalan.adoptafriend.server.user.User;

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
            e.printStackTrace();
        }
    }

    /*
     * Closes the connection to the Adopt-A-Friend database
     */
    public void shutdown() {
        try {
            database.disconnect();
        } catch (SQLException e) {
            System.err.print("There was an exception!");
            e.printStackTrace();
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
                + "desc CHAR(256),"
                + "photo BLOB,"
                + "UNIQUE (username),"
                + "UNIQUE (email)"
                + ");";
        String LISTING_TABLE = "CREATE TABLE IF NOT EXISTS listing ( "
                + "lid INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "owner INTEGER NOT NULL,"
                + "title CHAR(40) NOT NULL, "
                + "zip CHAR(10) NOT NULL, "
                + "type CHAR(40) NOT NULL, "
                + "sex CHAR(1) NOT NULL, "
                + "age INT(2) NOT NULL, "
                + "desc CHAR(256),"
                + "photo BLOB,"
                + "FOREIGN KEY (lid) REFERENCES user (uid)"
                + "); ";
        database.executeStatement(USER_TABLE);
        database.executeStatement(LISTING_TABLE);
    }

    /**
     * Inserts a new Listing record into the Database
     *
     * @param owner The user id for the listing owner
     * @param title The title of the listing
     * @param zip   The zip code for the animal in the listing
     * @param type  The type of animal in the listing
     * @param sex   The sex of the animal in the listing
     * @param age   The age of the animal in the listing
     * @param desc  The description of the listing
     * @param photo The photo file of the listing
     */
    public void insertListing(int owner, String title, String zip, String type, String sex, int age, String desc, File photo) {
        String query = "INSERT INTO listing (owner, title, zip, type, sex, age, desc, photo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setInt(1, owner);
            ps.setString(2, title);
            ps.setString(3, zip);
            ps.setString(4, type);
            ps.setString(5, sex);
            ps.setInt(6, age);
            ps.setString(7, desc);
            ps.setBinaryStream(8, new FileInputStream(photo), (int) photo.length());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.print("There was a file exception!");
            e.printStackTrace();
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
     * @param desc      The description of the user
     * @param photo     The photo file of the user
     */
    public void insertUser(String username, byte[] salt, String hash, String firstName, String lastName, String email, String phone, String street, String city, String state, String zip, String desc, File photo) {
        String query = "INSERT INTO user (username, passSalt, passHash, firstName, lastName, email, phone, street, city, state, zip, desc, photo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
            ps.setString(12, desc);
            ps.setBinaryStream(13, new FileInputStream(photo), (int) photo.length());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.print("There was a file exception!");
            e.printStackTrace();
        } finally {
            database.closeStatement(ps);
        }
    }

    /**
     * Updates an existing Listing record in the Database
     *
     * @param lid   The unique internal id for the listing
     * @param title The title of the listing
     * @param zip   The zip code for the animal in the listing
     * @param type  The type of animal in the listing
     * @param sex   The sex of the animal in the listing
     * @param age   The age of the animal in the listing
     * @param desc  The description of the listing
     * @param photo The photo file of the listing
     */
    public void updateListing(int lid, String title, String zip, String type, String sex, int age, String desc, File photo) {
        String query = "UPDATE listing SET title = ?, zip = ?, type = ?, sex = ?, age = ?, desc = ? "
                + "WHERE lid = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, title);
            ps.setString(2, zip);
            ps.setString(3, type);
            ps.setString(4, sex);
            ps.setInt(5, age);
            ps.setString(6, desc);
            ps.setBinaryStream(7, new FileInputStream(photo), (int) photo.length());
            ps.setInt(8, lid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.print("There was a file exception!");
            e.printStackTrace();
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
     * @param desc      The description of the user
     * @param photo     The photo file of the user
     */
    public void updateUser(int uid, String username, byte[] salt, String hash, String firstName, String lastName, String email, String phone, String street, String city, String state, String zip, String desc, File photo) {
        String query = "UPDATE user SET username = ?, passSalt = ?, passHash = ?, firstName = ?, lastName = ?, email = ?, "
                + "phone = ?, street = ?, city = ?, state = ?, zip = ?, desc = ?, photo = ? "
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
            ps.setString(12, desc);
            ps.setBinaryStream(13, new FileInputStream(photo), (int) photo.length());
            ps.setInt(14, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.print("There was a file exception!");
            e.printStackTrace();
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
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
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
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
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
                User user = new User(uid, uname);


                byte[] salt = rs.getBytes("passSalt");
                String hash = rs.getString("passHash");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String phone = rs.getString("phone");
                String desc = rs.getString("desc");

                user.setSalt(salt);
                user.setHash(hash);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setStreet(street);
                user.setCity(city);
                user.setState(state);
                user.setZip(zip);
                user.setPhone(phone);
                user.setDesc(desc);

                InputStream in = rs.getBinaryStream("photo");
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                int b = in.read();
                while (b >= 0) {
                    bout.write((char) b);
                    b = in.read();
                }
                File file = new File(uname + ".png");
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(bout.toByteArray());
                fout.close();
                user.setPhoto(file);

                return user;
            }
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("There was a file exception!");
            e.printStackTrace();
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
                User user = new User(id, uname);

                byte[] salt = rs.getBytes("passSalt");
                String hash = rs.getString("passHash");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String phone = rs.getString("phone");
                String desc = rs.getString("desc");

                user.setSalt(salt);
                user.setHash(hash);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setStreet(street);
                user.setCity(city);
                user.setState(state);
                user.setZip(zip);
                user.setPhone(phone);
                user.setDesc(desc);

                InputStream in = rs.getBinaryStream("photo");
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                int b = in.read();
                while (b >= 0) {
                    bout.write((char) b);
                    b = in.read();
                }
                File file = new File(uname + ".png");
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(bout.toByteArray());
                fout.close();
                user.setPhoto(file);

                return user;
            }
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("There was a file exception!");
            e.printStackTrace();
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
                String zip = rs.getString("zip");
                String type = rs.getString("type");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String desc = rs.getString("desc");

                listing.setTitle(title);
                listing.setZip(zip);
                listing.setType(type);
                listing.setSex(sex);
                listing.setAge(age);
                listing.setDesc(desc);

                InputStream in = rs.getBinaryStream("photo");
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                int b = in.read();
                while (b >= 0) {
                    bout.write((char) b);
                    b = in.read();
                }
                File file = new File(id + "__" + owner + ".png");
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(bout.toByteArray());
                fout.close();
                listing.setPhoto(file);

                return listing;
            }
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("There was a file exception!");
            e.printStackTrace();
        } finally {
            database.closeStatement(ps);
        }
        return null;
    }

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
                listing.setZip(rs.getString("zip"));
                listing.setType(rs.getString("type"));
                listing.setSex(rs.getString("sex"));
                listing.setAge(rs.getInt("age"));
                listing.setDesc(rs.getString("desc"));

                InputStream in = rs.getBinaryStream("photo");
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                int b = in.read();
                while (b >= 0) {
                    bout.write((char) b);
                    b = in.read();
                }
                File file = new File(lid + "__" + uid + ".png");
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(bout.toByteArray());
                fout.close();
                listing.setPhoto(file);

                listings.add(listing);
            }
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("There was a file exception!");
            e.printStackTrace();
        } finally {
            database.closeStatement(ps);
        }
        return listings;
    }

    /**
     * Selects all Listings that match the specified variables
     *
     * @param title The title of the listing
     * @param zip   The zip variable in the listing
     * @param type  The type variable in the listing
     * @param sex   The sex variable in the listing
     * @param age   The age variable in the listing
     * @return If records are found, returns an ArrayList of Listings
     */
    public List<Listing> selectListings(String title, String zip, String type, String sex, int age) {
        List<Listing> listings = new ArrayList<>();
        String query = "SELECT * FROM listing "
                + "WHERE UPPER(title) LIKE ? "
                + "AND UPPER(zip) = ? "
                + "AND UPPER(type) LIKE ? "
                + "AND UPPER(sex) = ? "
                + "AND age = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, "%" + title + "%");
            ps.setString(2, zip);
            ps.setString(3, "%" + type + "%");
            ps.setString(4, sex);
            ps.setInt(5, age);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("lid");
                int owner = rs.getInt("owner");
                Listing listing = new Listing(id, owner);

                listing.setTitle(rs.getString("title"));
                listing.setZip(rs.getString("zip"));
                listing.setType(rs.getString("type"));
                listing.setSex(rs.getString("sex"));
                listing.setAge(rs.getInt("age"));
                listing.setDesc(rs.getString("desc"));

                InputStream in = rs.getBinaryStream("photo");
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                int b = in.read();
                while (b >= 0) {
                    bout.write((char) b);
                    b = in.read();
                }
                File file = new File(id + "__" + owner + ".png");
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(bout.toByteArray());
                fout.close();
                listing.setPhoto(file);

                listings.add(listing);
            }
        } catch (SQLException e) {
            System.err.println("There was a SQL exception!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("There was a file exception!");
            e.printStackTrace();
        } finally {
            database.closeStatement(ps);
        }
        return listings;
    }

}
