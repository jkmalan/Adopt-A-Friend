package com.jkmalan.adoptafriend.interfaces.viewers;


import com.jkmalan.adoptafriend.interfaces.ProfileFrame;

import javax.swing.JFrame;

public class ProfileViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new ProfileFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Create an account");
      frame.setVisible(true);      
   }
}