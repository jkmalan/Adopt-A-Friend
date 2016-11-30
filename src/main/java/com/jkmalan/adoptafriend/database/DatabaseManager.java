package com.jkmalan.adoptafriend.database;

import com.jkmalan.adoptafriend.user.User;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {

	private final Database database;

	public DatabaseManager() {
        // 2
		database = new Database(new File(".\\adoptafriend.db"));
	}

	// check the tables

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

	// updates the tables

	public void updateTables() {

	}

	public void insertListing() {

    }

    public void insertUser() {

    }

    public void updateListing() {

    }

    public void updateUser() {
        /*
        PreparedStatement ps = database.getPreparedStatement("");
        ps.setString(1, "");
        ps.executeQuery();
        */
    }

    public void deleteListing() {

    }

    public void deleteUser() {
        /*
        PreparedStatement ps = database.getPreparedStatement("");
        ps.setString(1, "");
        ps.executeQuery();
        */
    }

    public List<User> getUsers() {
        try {
            PreparedStatement ps = database.getPreparedStatement("");
            ps.setString(1, "");
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    ResultSet rs = database.getPreparedStatement("").executeQuery(); // name, email, pass, desc
        while (rs.next()) {
        String name = rs.getString(1);
        String email = rs.getString(2);
        String pass = rs.getString(3);
        String desc = rs.getString(4);
        User user = new User(name, email, pass, desc);
    }
    */

}
