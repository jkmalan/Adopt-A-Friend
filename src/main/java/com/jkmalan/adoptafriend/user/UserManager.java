package com.jkmalan.adoptafriend.user;
import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.database.Database;

import java.sql.*;
import java.util.UUID;

public class UserManager {
	
	public final Database database;
	public UserManager user;
	String Query;
	PreparedStatement pstatement;

    public UserManager() {
    	
    	user=new UserManager();
    }

    public void createUser(int userID, String username, String password, String fName, String lName, 
    		String emailAddress, String phoneNumber, String streetAddress, String state, String zipCode,
    		String bio) {

    	String Query= "INSERT INTO User(" + "username," + "password" + "fName," +"lName,"
    	+ "emailAddress," + "phoneNumber," + "streetAddress" + "state," + "zipCode," + "bio,) VALUES"
    			+ "(null,?,?,?,?,?,?,?,?,?,?)";
    	try{
    		PreparedStatement pstatement=database.getPreparedStatement(Query);
    		
    		pstatement.setInt(1,userID);
    		pstatement.setString(2,username);
    		pstatement.setString(3, password);
    		pstatement.setString(4, fName);
    		pstatement.setString(5,lName);
    		pstatement.setString(6, emailAddress);
    		pstatement.setString(7, phoneNumber);
    		pstatement.setString(8, streetAddress);
    		pstatement.setString(9, state);
    		pstatement.setString(10, zipCode);
    		pstatement.setString(11,bio);
    		
    		pstatement.executeUpdate();
    		pstatement.close();
    		
    	}
    	catch(SQLException se)
    	{
    		
    	}
    }

    public void modifyUser(int userID, String username, String password, String fName, String lName, 
    		String emailAddress, String phoneNumber, String streetAddress, String state, String zipCode,
    		String bio) {
    	

    	String Query= "UPDATE User SET(" + "username," + "password" + "fName," +"lName,"
    	+ "emailAddress," + "phoneNumber," + "streetAddress" + "state," + "zipCode," + "bio,) VALUES"
    			+ "(null,?,?,?,?,?,?,?,?,?,?)";

    	try{
    		PreparedStatement pstatement=database.getPreparedStatement(Query);
    		
    		pstatement.setInt(1,userID);
    		pstatement.setString(2,username);
    		pstatement.setString(3, password);
    		pstatement.setString(4, fName);
    		pstatement.setString(5,lName);
    		pstatement.setString(6, emailAddress);
    		pstatement.setString(7, phoneNumber);
    		pstatement.setString(8, streetAddress);
    		pstatement.setString(9, state);
    		pstatement.setString(10, zipCode);
    		pstatement.setString(11,bio);
    		
    		pstatement.executeUpdate();
    		pstatement.close();
    		
    	}
    	catch(SQLException se)
    	{
    		
    	}
    }

    public void deleteUser(int uuid) {
    	String Query= "DELETE FROM user WHERE userID= ?";
    	try{
    		PreparedStatement pstatement=DatabaseManager().getPreparedStatement(Query);
    		pstatement.setInt(1,uuid);
    		
    		pstatement.executeUpdate();
    		pstatement.close();
    		
    	}
    	catch(SQLException se)
    	{
    		
    	}
    	

    }

}
