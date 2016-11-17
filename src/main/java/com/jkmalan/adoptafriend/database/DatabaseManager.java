package com.jkmalan.adoptafriend.database;

import java.sql.SQLException;

public class DatabaseManager {

	private final Database database;

	public DatabaseManager() {
		database = new Database();
	}

	// checks the tables

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

}
