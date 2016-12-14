package com.jkmalan.adoptafriend.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.User;

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
                + "firstName CHAR(24) NOT NULL,"
                + "lastName CHAR(24) NOT NULL,"
                + "email CHAR(60) NOT NULL,"
                + "street CHAR(45) NOT NULL,"
                + "city CHAR(24) NOT NULL,"
                + "state CHAR(2) NOT NULL,"
                + "zip CHAR(10) NOT NULL,"
                + "phone CHAR(10) NOT NULL,"
                + "desc CHAR(256),"
                + "photo CHAR(128),"
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
                + "photo CHAR(128),"
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
     * @param photo The photo path of the listing
     */
    public void insertListing(int owner, String title, String zip, String type, String sex, int age, String desc, String photo) {
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
            ps.setString(8, photo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("There was an exception!");
            e.printStackTrace();
        } finally {
            database.closeStatement(ps);
        }
    }

    /**
     * Inserts a new User record into the Database
     *
     * @param username  The unique username to use for logins
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The unique email to use for logins
     * @param phone     The phone number of the user
     * @param street    The street address of the user
     * @param city      The city of the user
     * @param state     The state of the user
     * @param zip       The zip code of the user
     * @param desc      The description of the user
     * @param photo     The photo path of the user
     */
    public void insertUser(String username, String firstName, String lastName, String email, String phone, String street, String city, String state, String zip, String desc, String photo) {
        String query = "INSERT INTO user (username, firstName, lastName, email, phone, street, city, state, zip, desc, photo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, username);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, street);
            ps.setString(7, city);
            ps.setString(8, state);
            ps.setString(9, zip);
            ps.setString(10, desc);
            ps.setString(11, photo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("There was an exception!");
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
     * @param photo The photo path of the listing
     */
    public void updateListing(int lid, String title, String zip, String type, String sex, int age, String desc, String photo) {
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
            ps.setString(7, photo);
            ps.setInt(8, lid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("There was an exception!");
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
     * @param firstName The first name of the user
     * @param lastName  The last name of the user
     * @param email     The unique email to use for logins
     * @param phone     The phone number of the user
     * @param street    The street address of the user
     * @param city      The city of the user
     * @param state     The state of the user
     * @param zip       The zip code of the user
     * @param desc      The description of the user
     * @param photo     The photo path of the user
     */
    public void updateUser(int uid, String username, String firstName, String lastName, String email, String phone, String street, String city, String state, String zip, String desc, String photo) {
        String query = "UPDATE user SET username = ?, firstName = ?, lastName = ?, email = ?, phone = ?,"
                + "street = ?, city = ?, state = ?, zip = ?, desc = ?, photo = ? "
                + "WHERE uid = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, username);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, street);
            ps.setString(7, city);
            ps.setString(8, state);
            ps.setString(9, zip);
            ps.setString(10, desc);
            ps.setString(11, photo);
            ps.setInt(12, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.print("There was an exception!");
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
            System.err.print("There was an exception!");
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
            System.err.print("There was an exception!");
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
                User user = new User(rs.getInt("uid"), rs.getString("username"));

                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String phone = rs.getString("phone");
                String desc = rs.getString("desc");

                user.setFistName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setStreet(street);
                user.setCity(city);
                user.setState(state);
                user.setZip(zip);
                user.setPhone(phone);
                user.setDesc(desc);

                String path = rs.getString("photo");
                File file = new File(path);
                user.setPhoto(file);

                return user;
            }
        } catch (SQLException e) {
            System.err.print("There was an exception!");
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
                User user = new User(rs.getInt("uid"), rs.getString("username"));

                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip");
                String phone = rs.getString("phone");
                String desc = rs.getString("desc");

                user.setFistName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setStreet(street);
                user.setCity(city);
                user.setState(state);
                user.setZip(zip);
                user.setPhone(phone);
                user.setDesc(desc);

                String path = rs.getString("photo");
                File file = new File(path);
                user.setPhoto(file);

                return user;
            }
        } catch (SQLException e) {
            System.err.print("There was an exception!");
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
                Listing listing = new Listing(lid, rs.getInt("owner"));

                listing.setTitle(rs.getString("title"));
                listing.setZip(rs.getString("zip"));
                listing.setType(rs.getString("type"));
                listing.setSex(rs.getString("sex"));
                listing.setAge(rs.getInt("age"));
                listing.setDesc(rs.getString("desc"));

                String path = rs.getString("photo");
                File file = new File(path);
                listing.setPhoto(file);

                return listing;
            }
        } catch (SQLException e) {
            System.err.print("There was an exception!");
            e.printStackTrace();
        } finally {
            database.closeStatement(ps);
        }
        return null;
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
                + "WHERE title LIKE ? ESCAPE ! "
                + "AND zip LIKE ? ESCAPE ! "
                + "AND type LIKE ? ESCAPE ! "
                + "AND sex LIKE ? ESCAPE ! "
                + "AND age = ?;";
        PreparedStatement ps = null;
        try {
            ps = database.getPreparedStatement(query);
            ps.setString(1, title);
            ps.setString(2, zip);
            ps.setString(3, type);
            ps.setString(4, sex);
            ps.setInt(5, age);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Listing listing = new Listing(rs.getInt("lid"), rs.getInt("owner"));

                listing.setTitle(rs.getString("title"));
                listing.setZip(rs.getString("zip"));
                listing.setType(rs.getString("type"));
                listing.setSex(rs.getString("sex"));
                listing.setAge(rs.getInt("age"));
                listing.setDesc(rs.getString("desc"));

                String path = rs.getString("photo");
                File file = new File(path);
                listing.setPhoto(file);

                listings.add(listing);
            }
        } catch (SQLException e) {
            System.err.print("There was an exception!");
            e.printStackTrace();
        } finally {
            database.closeStatement(ps);
        }
        return listings;

    }

}
