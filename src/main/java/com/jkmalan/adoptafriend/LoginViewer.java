package com.jkmalan.adoptafriend;


import javax.swing.JFrame;

public class LoginViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new LoginFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Adopt-A-Pet");
      frame.setVisible(true);      
   }
}