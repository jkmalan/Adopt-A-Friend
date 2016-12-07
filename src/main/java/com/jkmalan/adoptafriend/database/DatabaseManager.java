package com.jkmalan.adoptafriend.database;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.User;

public class DatabaseManager {

	private final Database database;

	public DatabaseManager() {

		database = new Database(new File(".\\adoptafriend.db"));
	}

	// check the tables - leave empty for now

	public void checkTables() {

	}

	// creates tables for Profile and Listings

	public void createTables() throws SQLException {

		String PROFILE_TABLE = "CREATE TABLE PROFILE ( " + " UID    CHAR   NOT NULL," + " UFName  CHAR(25)  NOT NULL,"
				+ " ULName   CHAR(25) NOT NULL," + " UEmail CHAR(60)  NOT NULL," + " UStreet CHAR(45) NOT NULL,"
				+ " UState  CHAR(2) NOT NULL," + " UZip    CHAR(10) NOT NULL," + " UPhone INT(10),"
				+ " UDescription CHAR(255)," + " PRIMARY KEY(UID));";
		String LISTING_TABLE = " CREATE TABLE LISTING ( " + " LID  CHAR(15)  NOT NULL, "
				+ " LTitle CHAR(40)  NOT NULL, " + " LAge   INT(2)    NOT NULL, " + " LType  CHAR(40)   NOT NULL, "
				+ " LSex   CHAR(1)     NOT NULL, " + " LAttributes CHAR(255) NOT NULL, " + " LDescription  CHAR(255), "
				+ " LRegion     CHAR(60)   NOT NULL, " + " PRIMARY KEY(LID), "
				+ " FOREIGN KEY(LID) REFERENCES PROFILE(UID)); ";

		database.executeStatement(PROFILE_TABLE);
		database.executeStatement(LISTING_TABLE);
	}

	// updates the tables - leave empty for now

	public void updateTables() {

	}

	// inserts a new listing

	public void insertListing(Listing listing, Database database) {
		String query = "INSERT INTO Listing (" + "uuid," + "title," + "sex," + "age," + "type," + "zip," + "desc,"
				+ "photos," + "attributes,) VALUES " + "(null, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setUUID(1, listing.getUUID());
			ps.setString(2, listing.getTitle());
			ps.setString(3, listing.getSex());
			ps.setString(4, listing.getAge());
			ps.setString(5, listing.getType());
			ps.setString(6, listing.getZip());
			ps.setString(7, listing.getDesc());
			ps.setFile(8, listing.getPhotos());
			ps.setString(9, listing.getAttributes());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {
			throw se;

		}
	}

	/*
	 * try { PreparedStatement ps = database.getPreparedStatement(
	 * "INSERT INTO Listing (uuid, title, sex, age, type, zip, desc, photos, attributes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * ); }
	 */

	// inserts a new user

	public void insertUser(User user, Database database) throws SQLException {
		String query = "INSERT INTO User (" + "uuid," + "username," + "userID," + "password," + "fName," + "lName,"
				+ "emailAddress," + "phoneNumber," + "streetAddress," + "state," + "zipCode," + "bio,) VALUES "
				+ "(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setUUID(1, user.getUUID());
			ps.setInt(2, user.getUserId());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getFirstName());
			ps.setString(6, user.getLastName());
			ps.setString(7, user.getEmail());
			ps.setString(8, user.getPhoneNum());
			ps.setString(9, user.getAddress());
			ps.setString(10, user.getState());
			ps.setString(11, user.getZip());
			ps.setString(12, user.getBio());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {
			throw se;

		}
	}
	/*
	 * try { PreparedStatement ps = database.getPreparedStatement(
	 * "INSERT INTO User (uuid, username, userID, password, fName, lName, emailAddress, phoneNumber, streetAddress, state, zipCode, bio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * );
	 * 
	 */

	// updates the listing

	public void updateListing(Listing listing, Database database) throws SQLException {

		String query = "UPDATE Listing SET title = ?, sex = ?, age = ?, type = ?, zip = ?, desc = ? WHERE uuid = ?";

		try {
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setString(1, "title");
			ps.setString(2, "sex");
			ps.setString(3, "age");
			ps.setString(4, "type");
			ps.setString(5, "zip");
			ps.setString(6, "desc");

			ps.executeUpdate();
			ps.close();

		} catch (SQLException se) {
			throw se;

		}

	}

	// updates the user

	public void updateUser(User user, Database database) throws SQLException {

		String query = "UPDATE User SET fName = ?, lName = ?, emailAddress = ?, phoneNumber = ?, streetAddress = ?, state = ?, zipCode = ?, bio =?  WHERE userId = ?";

		try {
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setString(1, "fName");
			ps.setString(2, "lName");
			ps.setString(3, "emailAddress");
			ps.setString(4, "phoneNumber");
			ps.setString(5, "streetAddress");
			ps.setString(6, "state");
			ps.setString(7, "zipCode");
			ps.setString(8, "bio");

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {
			throw se;

		}

	}

	/*
	 * PreparedStatement ps = database.getPreparedStatement(""); ps.setString(1,
	 * ""); ps.executeQuery();
	 */

	// deletes a Listing record

	public void deleteListing(Listing listing, Database database) throws SQLException {

		String deleteQuery = "DELETE FROM Listing WHERE uuid = ?";

		try {
			PreparedStatement ps = database.getPreparedStatement(deleteQuery);
			ps.setString(1, "?");

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {
			throw se;

		}
	}

	// deletes a User record

	public void deleteUser(User user, Database database) throws SQLException {

		String deleteQuery = "DELETE FROM User WHERE userId = ?";

		try {
			PreparedStatement ps = database.getPreparedStatement(deleteQuery);
			ps.setString(1, "?");

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {
			throw se;

		}
	}

	/*
	 * PreparedStatement ps = database.getPreparedStatement(""); ps.setString(1,
	 * ""); ps.executeQuery();
	 */

	// list the users
	public List<User> getUsers() {
		try {
			PreparedStatement ps = database.getPreparedStatement("username");
			ps.setString(1, "?");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String username = rs.getString(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// list the listings
	public List<Listing> getListings() {
		try {
			PreparedStatement ps = database.getPreparedStatement("uuid");
			ps.setString(1, "?");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UUID uuid = rs.getUUID(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}

/*
 * ResultSet rs = database.getPreparedStatement("").executeQuery(); // name,
 * email, pass, desc while (rs.next()) { String name = rs.getString(1); String
 * email = rs.getString(2); String pass = rs.getString(3); String desc =
 * rs.getString(4); User user = new User(name, email, pass, desc); }
 */
