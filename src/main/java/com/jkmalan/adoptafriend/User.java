package com.jkmalan.adoptafriend;

public class User {
	
private String username;
protected Integer userId;
private String password;

public User(String username, String password, Integer userId) {

this.username= username;
this.password=password;
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


}

