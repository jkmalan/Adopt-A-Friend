package com.jkmalan.adoptafriend.interfaces;

import javax.swing.JFrame;

public class EditListingFrameViewer {
	public static void main(String[] args)
	   {  
	      JFrame frame = new EditListingFrame();
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setTitle("Edit Listing");
	      frame.setVisible(true);      
	   }
	}