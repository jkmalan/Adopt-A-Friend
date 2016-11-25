package com.jkmalan.adoptafriend.search;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class SearchManager implements ResultSet {

		Connection connect=DriverManager.getConnection("");	//connects to DB
		Statement search=connect.createStatement();	//creates java statement
		String Query= "SELECT * FROM ";	//sql query
		ResultSet rs=search.executeQuery(Query);	//Runs query and gets javaresult
	
		PreparedStatement preparedStatement=null;
		
	public Resultset search(String...attributes)
	{
		preparedStatement=dbConnection.prepareStatement(Query);
		preparedStatement.setArray(attributes);
		ResultSet rs=preparedStatement.executeQuery();
	
		while(rs.next())
		{
			String attributes=rs.getString("Attributes");
			System.out.prinln("Attributes: "+ attributes +"\n");
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
			
			System.out.printlin("Username: " + username +"\n UserID: " + userId + "\n");
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
}