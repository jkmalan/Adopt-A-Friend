package com.jkmalan.adoptafriend.interfaces;

import javax.swing.JFrame;

public class UserListingViewer { 
	public static void main(String[] args)
	   { 
JFrame frame = new UserListingFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setTitle("Current Listings");
frame.setVisible(true);
}}
