package com.jkmalan.adoptafriend.interfaces.viewers;


import com.jkmalan.adoptafriend.interfaces.LoginPage;

import javax.swing.JFrame;

public class LoginViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new LoginPage();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Adopt-A-Pet");
      frame.setVisible(true);      
   }
}