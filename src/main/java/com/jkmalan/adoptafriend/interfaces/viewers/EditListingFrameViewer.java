package com.jkmalan.adoptafriend.interfaces.viewers;

import com.jkmalan.adoptafriend.interfaces.EditListingPage;

import javax.swing.JFrame;

public class EditListingFrameViewer {
	public static void main(String[] args)
	   {  
	      JFrame frame = new EditListingPage();
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setTitle("Edit Listing");
	      frame.setVisible(true);      
	   }
	}
