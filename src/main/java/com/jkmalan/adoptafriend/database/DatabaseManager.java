package com.jkmalan.adoptafriend.database;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.sql.*;

import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.User;

public class DatabaseManager {

	private final Database database;
	
	public DatabaseManager() {
		database = new Database(new File(".\\adoptafriend.db"));
	}

	// creates tables for Profile and Listings

	
	public void createTables() throws SQLException {

		String PROFILE_TABLE = "CREATE TABLE PROFILE ( "
                + " userId    INT(15)   NOT NULL AUTO_INCREMENT,"
				+ " username CHAR(20) NOT NULL"
                + " fName  CHAR(25)  NOT NULL,"
                + " lName   CHAR(25) NOT NULL,"
				+ " emailAddress CHAR(60)  NOT NULL,"
                + " streetAddress CHAR(45) NOT NULL,"
				+ " state  CHAR(2) NOT NULL,"
                + " zipCode    CHAR(10) NOT NULL,"
                + " phoneNumber CHAR(10) NOT NULL,"
				+ " bio CHAR(255),"
                + " PRIMARY KEY(userId));";
		String LISTING_TABLE = " CREATE TABLE LISTING ( "
                + " lid  INT(15)  NOT NULL AUTO_INCREMENT,"
                + " title CHAR(40)  NOT NULL, "
				+ " age   INT(2)    NOT NULL, "
                + " type  CHAR(40)   NOT NULL, "
                + " sex   CHAR(1)     NOT NULL, "
				+ " attributes CHAR(255), "
                + " desc CHAR(255)," + "photos CHAR(128),"
                + " zip     CHAR(10)   NOT NULL, "
				+ " PRIMARY KEY(lid), "
                + " FOREIGN KEY(lid) REFERENCES PROFILE(userId)); ";

		database.executeStatement(PROFILE_TABLE);
		database.executeStatement(LISTING_TABLE);
	}

	// inserts a new listing

	public void insertListing(int lid, String title, String sex, int age, String type, String zip, String desc,
			String photos, String attributes) {

		String query = "INSERT INTO Listing (title, sex, age, type, zip, desc, photos, attributes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setString(1, title);
			ps.setString(2, sex);
			ps.setInt(3, age);
			ps.setString(4, type);
			ps.setString(5, zip);
			ps.setString(6, desc);
			ps.setString(7, photos);
			ps.setString(8, attributes);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {

		}
	}


	// inserts a new user

	public void insertUser(int userId, String username, String password, String fName, String lName,
			String emailAddress, String phoneNumber, String streetAddress, String state, String zipCode, String bio) {

		String query = "INSERT INTO User (username, password, fName, lName, emailAddress, phoneNumber, streetAddress, state, zipCode, bio) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = database.getPreparedStatement(query);

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, fName);
			ps.setString(4, lName);
			ps.setString(5, emailAddress);
			ps.setString(6, phoneNumber);
			ps.setString(7, streetAddress);
			ps.setString(8, state);
			ps.setString(9, zipCode);
			ps.setString(10, bio);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {

		}
	}

	// updates the listing

	public void updateListing(String title, String sex, int age, String type, String zip, String desc, int lid) {

		String query = "UPDATE Listing SET title = ?, sex = ?, age = ?, type = ?, zip = ?, desc = ? WHERE lid = ?";

		try {
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setString(1, title);
			ps.setString(2, sex);
			ps.setInt(3, age);
			ps.setString(4, type);
			ps.setString(5, zip);
			ps.setString(6, desc);
            ps.setInt(7, lid);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {

		}

	}

	// updates the user

	public void updateUser(String fName, String lName, String emailAddress, String phoneNumber, String streetAddress,
			String state, String zipCode, String bio, int uid) {

		String query = "UPDATE User SET fName = ?, lName = ?, emailAddress = ?, phoneNumber = ?, streetAddress = ?, state = ?, zipCode = ?, bio =?  WHERE userId = ?";

		try {
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setString(3, emailAddress);
			ps.setString(4, phoneNumber);
			ps.setString(5, streetAddress);
			ps.setString(6, state);
			ps.setString(7, zipCode);
			ps.setString(8, bio);
            ps.setInt(9, uid);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {

		}

	}

	

	// deletes a Listing record

	public void deleteListing(int lid) {

		String deleteQuery = "DELETE FROM Listing WHERE lid = ?";

		try {
			PreparedStatement ps = database.getPreparedStatement(deleteQuery);
			ps.setInt(1, lid);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {

		}
	}

	// deletes a User record

	public void deleteUser(int userID) {

		String deleteQuery = "DELETE FROM User WHERE userId = ?";

		try {
			PreparedStatement ps = database.getPreparedStatement(deleteQuery);
			ps.setInt(1, userID);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {

		}
	}



	// list the users
	public User selectUser(int uid) {
		try {
			String query = "SELECT * FROM User WHERE userID = ?";
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setInt(1, uid);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
                User user = new User(rs.getInt("userId"));

				String username = rs.getString("username");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String emailAddress = rs.getString("emailAddress");
                String streetAddress = rs.getString("streetAddress");
                String state = rs.getString("state");
                String zipCode = rs.getString("zipCode");
                String phoneNumber = rs.getString("phoneNumber");
                String bio = rs.getString("bio");

                user.setFistName(fName);
                user.setLastName(lName);
                user.setEmail(emailAddress);
                user.setStreetAddress(streetAddress);
                user.setState(state);
                user.setZipCode(zipCode);
                user.setPhoneNumber(phoneNumber);
                user.setBio(bio);
                return user;
			}
		} catch (SQLException e) {
            // TODO Handle the error
		}
		return null;

	}

	public Listing selectListing(int lid) {
		try {
			String query = "SELECT * FROM Listings WHERE lid= ?";
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setInt(1, lid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
                Listing listing = new Listing(rs.getInt("lid"));

                String title = rs.getString("title");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String type = rs.getString("type");
                String zip = rs.getString("zip");
                String desc = rs.getString("desc");

                listing.setTitle(title);
                listing.setSex(sex);
                listing.setAge(age);
                listing.setType(type);
                listing.setZip(zip);
                listing.setDesc(desc);

                return listing;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

    /**
     * Selects all listings that match the search variables
     *
     * @param title The title of the listing
     * @param sex The sex of the listing animal
     * @param age The age of the listing animal
     * @return A listing of listings that match the search variables, or null if none found
     */
	public List<Listing> selectListings(String title, String sex, int age) {
		try {
			String query = "SELECT * FROM Listings";
			PreparedStatement ps = database.getPreparedStatement("uuid");
			ps.setString(1, "");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// UUID uuid = rs.getUUID(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * Prelim public Resultset search(String...attributes) {
	 * 
	 * preparedStatement=dbConnection.prepareStatement(Query);
	 * preparedStatement.setArray(attributes); ResultSet
	 * rs=preparedStatement.executeQuery();
	 * 
	 * while(rs.next()) { String attributes=rs.getString("Attributes");
	 * System.out.println("Attributes: "+ attributes +"\n"); } search.close(); }
	 * public ResultSet search(String type, String age, String...attributes) {
	 * preparedStatement=dbConnection.prepareStatement(Query);
	 * preparedStatement.setArray(type, age, attributes); ResultSet
	 * rs=preparedStatement.executeQuery();
	 * 
	 * while(rs.next()) { String type=rs.getString("breed"); String age=
	 * rs.getString("age"); String attributes=rs.getString("attributes");
	 * 
	 * System.out.println("Breed: " + breed +"\n"); System.out.println("Age: " +
	 * age +"\n"); System.out.println("Attributes: " + attributes +"\n"); }
	 * search.close(); } public List<User> getUsers(String...attributes) {
	 * Query="SELECT * FROM Users";
	 * preparedStatement=dbConnection.prepareStatement(Query);
	 * preparedStatement.setArray(username, userID); ResultSet
	 * rs=preparedStatement.executeQuery();
	 * 
	 * while(rs.next()) { String username=rs.getString("User"); String
	 * userId=rs.getString("userId");
	 * 
	 * System.out.println("Username: " + username +"\n UserID: " + userId +
	 * "\n"); } search.close();
	 * 
	 * } public List<Listing>getListings(String...attributes) {
	 * preparedStatement=dbConnection.prepareStatement(Query);
	 * preparedStatement.setArray(attributes); ResultSet
	 * rs=preparedStatement.executeQuery();
	 * 
	 * while(rs.next()) { String attribtues=rs.getString("attributes");
	 * 
	 * System.out.println("Attributes: " + attributes + "\n"); } search.close();
	 * } public List<Listing>getListings(String terms) { term.split(" ");
	 * getListings();
	 * 
	 * while(rs.next()) { String terms=rs.getString("Term");
	 * System.out.println("Terms: "+ terms +"\n"); } } public final
	 * PreparedStatement getPreparedStatement(String query) { return
	 * prepareStatement(query);
	 * 
	 * } public final void executeStatement(String query) throws SQLException {
	 * Statement search=getStatement(); search.execute(query);
	 * closeStatement(search); }
	 */
}

/*
 * ResultSet rs = database.getPreparedStatement("").executeQuery(); // name,
 * email, pass, desc while (rs.next()) { String name = rs.getString(1); String
 * email = rs.getString(2); String pass = rs.getString(3); String desc =
 * rs.getString(4); User user = new User(name, email, pass, desc); }
 */
