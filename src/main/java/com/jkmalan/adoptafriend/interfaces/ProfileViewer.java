package com.jkmalan.adoptafriend.interfaces;


import javax.swing.JFrame;

public class ProfileViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new ProfileFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Create your account");
      frame.setVisible(true);      
   }
}