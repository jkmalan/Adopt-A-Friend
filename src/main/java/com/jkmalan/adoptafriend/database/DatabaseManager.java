package com.jkmalan.adoptafriend.database;

import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.User;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {

    private final File file = new File("");
	private final Database database;

	public DatabaseManager() {
		database = new Database(file);
	}

	public void createTables() throws SQLException {
        String ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS `account` ("
                + "`uid` INTEGER NOT NULL"
                + "`hash` CHAR(40) NOT NULL"
                + "PRIMARY KEY (`uid`)"
                + "FOREIGN KEY (`uid`) REFERENCES USER (`uid`)";
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
                + "PRIMARY KEY (`uid`)";
        String LISTING_TABLE = "CREATE TABLE IF NOT EXISTS `user` ( "
                + "`lid` INTEGER NOT NULL AUTO_INCREMENT"
                + "`owner` INTEGER NOT NULL"
                + "`title` CHAR(16) NOT NULL"
                + "`zip` CHAR(5) NOT NULL"
                + "`type` CHAR(12)"
                + "`age` INTEGER"
                + "`sex` CHAR(1)"
                + "`desc` CHAR(256)"
                + "`photo` CHAR(128)"
                + "PRIMARY KEY (`lid`)"
                + "FOREIGN KEY (`owner`) REFERENCES `user` (`uid`)";
        database.getStatement().execute(USER_TABLE);
		database.getStatement().execute(ACCOUNT_TABLE);
		database.getStatement().execute(LISTING_TABLE);
	}

    public void insertUser() {

    }

	public void insertListing() {

    }

    public void updateUser() {

    }

    public void updateListing() {

    }

    public void deleteUser() {

    }

    public void deleteListing() {

    }

    public List<User> selectUsers() {
        return null;
    }

	public List<Listing> selectListings() {
		return null;
	}

}
