package com.jkmalan.adoptafriend.search;

import java.sql.*;
import java.util.ArrayList;

/***
 * 
 *Converts user's search 
 *into Search Queries
 * @author blin021
 *
 */
public class SearchQuery {

	public static void main(String []args)
	{
		try
		{
			Connection connect=DriverManager.getConnection("");	//connects to DB
			Statement search=connect.createStatement();	//creates java statement
			String Query= "SELECT * FROM ";	//sql query
			ResultSet rs=search.executeQuery(Query);	//Runs query and gets javaresult
			
			while(rs.next())	//prints search result, iterable
			{
				String name=rs.getString("name");
				String breed=rs.getString("Breed");
				String gender=rs.getString("gender");
				int age=rs.getInt("age");
				String location=rs.getString("location");
				
				System.out.format("%s, %s, %s, %s, %s \n", name, breed, gender, age, location); 
				//display results
				
			}
			search.close();
		}
		catch(Exception e)
		{
		 	System.err.println("Exception");
	    	System.err.println(e.getMessage());
		}
	}

}
