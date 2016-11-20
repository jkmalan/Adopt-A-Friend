package com.jkmalan.adoptafriend;

public class UserProfile extends User{
	
	private String fName;
	private String lName;
	private String emailAddress;
	private String phoneNumber;
	private String streetAddress;
	private String state;
	private String zipCode;
	private String bio;
	
	
public UserProfile(String username, String password, Integer userId, String fName, String lName, String emailAddress, String phoneNumber, String streetAddress, String state, String zipCode, String bio)
{
	super(username, password, userId);
	this.fName=fName;
	this.lName=lName;
	this.emailAddress=emailAddress;
	this.phoneNumber=phoneNumber;
	this.streetAddress=streetAddress;
	this.state=state;
	this.zipCode=zipCode;
	this.bio=bio;
	
}

public Integer getUserId()
{
	return userId;
}

public String getUser()
{
	return getUserName();
	
}



		
		
	

}
