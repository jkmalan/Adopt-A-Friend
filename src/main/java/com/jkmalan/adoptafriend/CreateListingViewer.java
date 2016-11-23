package com.jkmalan.adoptafriend;


import javax.swing.JFrame;

public class CreateListingViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new CreateListingFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Create a Listing");
      frame.setVisible(true);      
   }
}
