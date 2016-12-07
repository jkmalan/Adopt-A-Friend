package com.jkmalan.adoptafriend.database;

import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.User;
import com.mysql.jdbc.ResultSetImpl;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseManager {

    private final File file = new File("");
	private final Database database;

	public DatabaseManager() {
		database = new Database(file);
        try {
            database.connect();
        } catch (SQLException e) {
            // TODO Handle errors
        }
    }

	public void createTables() throws SQLException {
        String ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS `account` ("
                + "`uid` INTEGER NOT NULL"
                + "`hash` CHAR(40) NOT NULL"
                + "PRIMARY KEY (`uid`)"
                + "FOREIGN KEY (`uid`) REFERENCES USER (`uid`)"
                + ");";
        String USER_TABLE = "CREATE TABLE IF NOT EXISTS `user` ( "
                + "`uid` INTEGER NOT NULL AUTO_INCREMENT"
                + "`name` CHAR(16) NOT NULL"
                + "`email` CHAR(32) NOT NULL"
                + "`phone` CHAR(10)"
                + "`street` CHAR(48)"
                + "`city` CHAR(16)"
                + "`state` CHAR(2) NOT NULL"
                + "`zip` CHAR(5) NOT NULL"
                + "`desc` CHAR(256)"
                + "`photo` CHAR(128)"
                + "PRIMARY KEY (`uid`)"
                + ");";
        String LISTING_TABLE = "CREATE TABLE IF NOT EXISTS `listing` ( "
                + "`lid` INTEGER NOT NULL AUTO_INCREMENT"
                + "`owner` INTEGER NOT NULL"
                + "`title` CHAR(16) NOT NULL"
                + "`zip` CHAR(5) NOT NULL"
                + "`type` CHAR(12)"
                + "`sex` CHAR(1)"
                + "`age` INTEGER"
                + "`desc` CHAR(256)"
                + "`photo` CHAR(128)"
                + "PRIMARY KEY (`lid`)"
                + "FOREIGN KEY (`owner`) REFERENCES `user` (`uid`)"
                + ");";
        database.getStatement().execute(USER_TABLE);
		database.getStatement().execute(ACCOUNT_TABLE);
		database.getStatement().execute(LISTING_TABLE);
	}

    public void insertUser() {
        String USER_INSERT = "";
    }

	public void insertListing() {
        String LISTING_INSERT = "";
    }

    public void updateUser() {
        String USER_UPDATE = "";
    }

    public void updateListing() {
        String LISTING_UPDATE = "";
    }

    public void deleteUser() {
        String USER_DELETE = "";
    }

    public void deleteListing() {
        String LISTING_DELETE = "";
    }

    public Optional<User> selectUser(Integer uid) {
        String USER_QUERY = "SELECT * FROM `user` WHERE `uid` = ?;";
        try {
            PreparedStatement ps = database.getPreparedStatement(USER_QUERY);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(uid);

                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setStreet(rs.getString("street"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setDesc(rs.getString("desc"));

                String path = rs.getString("photo");
                File file = new File(path);
                user.setPhoto(file);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        }
        return Optional.empty();
    }

    public Optional<Listing> selectListing(Integer lid) {
        String LISTING_QUERY = "SELECT * FROM `listing` WHERE `lid` = ?;";
        try {
            PreparedStatement ps = database.getPreparedStatement(LISTING_QUERY);
            ps.setInt(1, lid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer owner = rs.getInt("owner");
                Listing listing = new Listing(lid, owner);

                listing.setTitle(rs.getString("title"));
                listing.setZip(rs.getString("zip"));
                listing.setType("type");
                listing.setSex("sex");
                listing.setAge("age");
                listing.setDesc("desc");

                String path = rs.getString("photo");
                File file = new File(path);
                listing.setPhoto(file);

                return Optional.of(listing);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        }
        return Optional.empty();
    }

	public List<Listing> selectListings(String title, String zip, String type, String sex, Integer age) {
        List<Listing> listings = new ArrayList<>();
        String LISTING_QUERY = "SELECT lid FROM `listing` "
                + "WHERE `title` LIKE ? "
                + "AND `zip` LIKE ? "
                + "AND `type` LIKE ? "
                + "AND `sex` LIKE ? "
                + "AND `age` = ?;";
        try {
            PreparedStatement ps = database.getPreparedStatement(LISTING_QUERY);
            ps.setString(1, title);
            ps.setString(2, zip);
            ps.setString(3, type);
            ps.setString(4, sex);
            ps.setInt(5, age);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer lid = rs.getInt("lid");
                Integer owner = rs.getInt("owner");
                Listing listing = new Listing(lid, owner);

                listing.setTitle(rs.getString("title"));
                listing.setZip(rs.getString("zip"));
                listing.setType("type");
                listing.setSex("sex");
                listing.setAge("age");
                listing.setDesc("desc");

                String path = rs.getString("photo");
                File file = new File(path);
                listing.setPhoto(file);

                listings.add(listing);
            }
        } catch (SQLException e) {
            // TODO Handle errors
        }
        return listings;
	}

}
