package com.jkmalan.adoptafriend.interfaces;

import javax.swing.JFrame;

public class EditProfileViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new EditProfileFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Edit your account");
      frame.setVisible(true);      
   }
}