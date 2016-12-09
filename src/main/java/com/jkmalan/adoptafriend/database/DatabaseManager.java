package com.jkmalan.adoptafriend.database;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.sql.*;

import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.User;

public class DatabaseManager {

	private final Database database;
	Connection dbConnection=DriverManager.getConnection("");	//connects to DB
	Statement search=dbConnection.createStatement();	//creates java statement
	String Query= "SELECT * FROM ";	//sql query
	ResultSet rs=search.executeQuery(Query);	//Runs query and gets javaresult

	PreparedStatement preparedStatement=null;

	public DatabaseManager() {

		database = new Database(new File(".\\adoptafriend.db"));
	}

	// check the tables - leave empty for now

	public void checkTables() {

	}

	// creates tables for Profile and Listings

	// change the names to match the queries
	public void createTables() throws SQLException {

		String PROFILE_TABLE = "CREATE TABLE PROFILE ( " + " userId    INT(15)   NOT NULL,"
				+ " username CHAR(20) NOT NULL" + " fName  CHAR(25)  NOT NULL," + " lName   CHAR(25) NOT NULL,"
				+ " emailAddress CHAR(60)  NOT NULL," + " streetAddress CHAR(45) NOT NULL,"
				+ " state  CHAR(2) NOT NULL," + " zipCode    CHAR(10) NOT NULL," + " phoneNumber CHAR(10) NOT NULL,"
				+ " bio CHAR(255)," + " PRIMARY KEY(userId));";
		String LISTING_TABLE = " CREATE TABLE LISTING ( " + " lid  INT(15)  NOT NULL, " + " title CHAR(40)  NOT NULL, "
				+ " age   INT(2)    NOT NULL, " + " type  CHAR(40)   NOT NULL, " + " sex   CHAR(1)     NOT NULL, "
				+ " attributes CHAR(255), " + "desc CHAR(255)," + "photos CHAR(128)," + " zip     CHAR(10)   NOT NULL, "
				+ " PRIMARY KEY(lid), " + " FOREIGN KEY(lid) REFERENCES PROFILE(userId)); ";

		database.executeStatement(PROFILE_TABLE);
		database.executeStatement(LISTING_TABLE);
	}

	// updates the tables - leave empty for now

	public void updateTables() {

	}

	// inserts a new listing

	public void insertListing(int lid, String title, String sex, int age, String type, String zip, String desc,
			String photos, String attributes) {

		String query = "INSERT INTO Listing (" + "lid," + "title," + "sex," + "age," + "type," + "zip," + "desc,"
				+ "photos," + "attributes,) VALUES " + "(null, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = database.getPreparedStatement(query);
			ps.setInt(1, lid);
			ps.setString(2, title);
			ps.setString(3, sex);
			ps.setInt(4, age);
			ps.setString(5, type);
			ps.setString(6, zip);
			ps.setString(7, desc);
			ps.setString(8, photos);
			ps.setString(9, attributes);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {

		}
	}

	/*
	 * try { PreparedStatement ps = database.getPreparedStatement(
	 * "INSERT INTO Listing (uuid, title, sex, age, type, zip, desc, photos, attributes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * ); }
	 */

	// inserts a new user

	public void insertUser(int userId, String username, String password, String fName, String lName,
			String emailAddress, String phoneNumber, String streetAddress, String state, String zipCode, String bio) {

		String query = "INSERT INTO User (" + "username," + "userId," + "password," + "fName," + "lName,"
				+ "emailAddress," + "phoneNumber," + "streetAddress," + "state," + "zipCode," + "bio,) VALUES "
				+ "(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = database.getPreparedStatement(query);

			ps.setInt(1, userId);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, fName);
			ps.setString(5, lName);
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
	/*
	 * try { PreparedStatement ps = database.getPreparedStatement(
	 * "INSERT INTO User (uuid, username, userID, password, fName, lName, emailAddress, phoneNumber, streetAddress, state, zipCode, bio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * );
	 * 
	 */

	// updates the listing

	public void updateListing(String title, String sex, int age, String type, String zip, String desc) {

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

		}

	}

	// updates the user

	public void updateUser(String fName, String lName, String emailAddress, String phoneNumber, String streetAddress,
			String state, String zipCode, String bio) {

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

			ps.executeUpdate();
			ps.close();
		} catch (SQLException se) {

		}

	}

	/*
	 * PreparedStatement ps = database.getPreparedStatement(""); ps.setString(1,
	 * ""); ps.executeQuery();
	 */

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

	// Stop after this

	/*
	 * PreparedStatement ps = database.getPreparedStatement(""); ps.setString(1,
	 * ""); ps.executeQuery();
	 */

	// list the users
	public User selectUser() {
		try {
			PreparedStatement ps = database.getPreparedStatement("select ...");
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
	
	public Listing SelectListings()
	{
		String Query= "SELECT * WHERE=?";
		return Listing;
	}

	// list the listings
	public List<Listing> selectListings() {
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

	public Resultset search(String...attributes)
	{

		preparedStatement=dbConnection.prepareStatement(Query);
		preparedStatement.setArray(attributes);
		ResultSet rs=preparedStatement.executeQuery();
	
		while(rs.next())
		{
			String attributes=rs.getString("Attributes");
			System.out.println("Attributes: "+ attributes +"\n");
		}
		search.close();
	}
	public ResultSet search(String type, String age, String...attributes)
	{
		preparedStatement=dbConnection.prepareStatement(Query);
		preparedStatement.setArray(type, age, attributes);
		ResultSet rs=preparedStatement.executeQuery();
		
		while(rs.next())
		{
			String type=rs.getString("breed");
			String age= rs.getString("age");
			String attributes=rs.getString("attributes");
			
			System.out.println("Breed: " + breed +"\n");
			System.out.println("Age: " + age +"\n");
			System.out.println("Attributes: " + attributes +"\n");
		}
		search.close();
	}
	public List<User> getUsers(String...attributes)
	{
		preparedStatement=dbConnection.prepareStatement(Query);
		preparedStatement.setArray(username, userId);
		ResultSet rs=preparedStatement.executeQuery();
		
		while(rs.next())
		{
			String username=rs.getString("User");
			String userId=rs.getString("userId");
			
			System.out.println("Username: " + username +"\n UserID: " + userId + "\n");
		}
		search.close();
		
	}
	public List<Listing>getListings(String...attributes)
	{
		preparedStatement=dbConnection.prepareStatement(Query);
		preparedStatement.setArray(attributes);
		ResultSet rs=preparedStatement.executeQuery();
		
		while(rs.next())
		{
			String attribtues=rs.getString("attributes");
			
			System.out.println("Attributes: " + attributes + "\n");
		}
		search.close();
	}
	public List<Listing>getListings(String terms)
	{
		term.split(" ");
		getListings();
		
		while(rs.next())
		{
			String terms=rs.getString("Term");
			System.out.println("Terms: "+ terms +"\n");
		}
	}
	public final PreparedStatement getPreparedStatement(String query) 
	{
		return prepareStatement(query);
		
	}
	public final void executeStatement(String query) throws SQLException
	{
		Statement search=getStatement();
		search.execute(query);
		closeStatement(search);
	}
}

/*
 * ResultSet rs = database.getPreparedStatement("").executeQuery(); // name,
 * email, pass, desc while (rs.next()) { String name = rs.getString(1); String
 * email = rs.getString(2); String pass = rs.getString(3); String desc =
 * rs.getString(4); User user = new User(name, email, pass, desc); }
 */
