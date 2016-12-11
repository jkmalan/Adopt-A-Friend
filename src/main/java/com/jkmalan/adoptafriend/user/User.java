package com.jkmalan.adoptafriend.user;

import java.util.UUID;

public class User {

	private String username;
    protected Integer userId;
    private String password;
    private String fName;
    private String lName;
    private String emailAddress;
    private String phoneNumber;
    private String streetAddress;
    private String state;
    private String zipCode;
    private String bio;
    
  /*  private List<File> ;
    private List<String>;*/

    public User(int userId)
    {

        this.userId=userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }

    public String getUserName() 
    {
        return username;
    }   
    
    public void setFistName(String fName)
    {
    	this.fName=fName;
    }
    
    public String getFirstName()
    {
    	return fName;
    }
    
    public void setLastName(String lName)
    {
    	this.lName=lName;
    }
    
    public String getLastName()
    {
    	return lName;
    }
    
    public void setEmail(String emailAddress)
    {
    	this.emailAddress=emailAddress;
    }
    
    public String getEmail()
    {
    	return emailAddress;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
    	this.phoneNumber=phoneNumber;
    }
    
    public String getPhoneNum()
    {
    	return phoneNumber;
    }
    
    public void setStreetAddress(String streetAddress)
    {
    	this.streetAddress=streetAddress;
    }
    
    public String getAddress()
    {
    	return streetAddress;
    }
    
    public void setState(String state)
    {
    	this.state=state;	
    }
    
    public String getState()
    {
    	return state;
    }
    
    public void setZipCode(String zipCode)
    {
    	this.zipCode=zipCode;
    }
    
    public String getZip()
    {
    	return zipCode;
    }
    
    public void setBio(String bio)
    {
    	this.bio=bio;
    }
    
    public String getBio()
    {
		return bio;
    }
    
    


}

