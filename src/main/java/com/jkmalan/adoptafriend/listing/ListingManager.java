package com.jkmalan.adoptafriend.listing;

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.database.Database;

import java.sql.*;
import java.util.UUID;

public class ListingManager {

	public final Database database;
	public ListingManager listing;

	String Query;
	PreparedStatement pstatement;

		ResultSet rs=pstatement.executeQuery(Query);
	
    public ListingManager() {
    	listing=new ListingManager();
    }

    public void createListing(int lid, String title, String sex, int age, String type, String zip, String desc, String photos, String attributes) {

    	String Query= "INSERT INTO Listing(" + "lid," + "title," + "sex," +"age" +"type," + 
    	"zip," + "desc," + "photos," + "attributes,) VALUES " + "(NULL, ?,?,?,?,?,?,?,?,?)";
    	try{
    		PreparedStatement pstatement=database.getPreparedStatement(Query);
    		pstatement.setInt(1, lid);
    		pstatement.setString(2, title);
    		pstatement.setString(3, sex);
    		pstatement.setInt(4, age);
    		pstatement.setString(5, type);
    		pstatement.setString(6, zip);
    		pstatement.setString(7, desc);
    		pstatement.setString(8, photos);
    		pstatement.setString(9, attributes);
    		
    		pstatement.executeUpdate();
    		pstatement.close();
    	}
    	catch(SQLException se)
    	{
    		
    	}
    }

    public void modifyListing(int lid, String title, String sex, int age, String type, String zip, String desc, String photos, String attributes) {

    	String Query="UPDATE Listing SET(" + "lid," + "title," + "sex," +"age" +"type," + 
    	"zip," + "desc," + "photos," + "attributes,) VALUES " + "(NULL, ?,?,?,?,?,?,?,?,?)";
    	try{
    		PreparedStatement pstatement=database.getPreparedStatement(Query);
       		pstatement.setInt(1, lid);
    		pstatement.setString(2, title);
    		pstatement.setString(3, sex);
    		pstatement.setInt(4, age);
    		pstatement.setString(5, type);
    		pstatement.setString(6, zip);
    		pstatement.setString(7, desc);
    		pstatement.setString(8, photos);
    		pstatement.setString(9, attributes);
    		
    		pstatement.executeUpdate();
    		pstatement.close();
    	}
    	catch(SQLException se)
    	{
    		
    	}
    }

    public void deleteListing(int uuid) {

    	String Query= "DELETE FROM Listing WHERE LID= ?";
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
