package com.jkmalan.adoptafriend.interfaces;

import javax.swing.JFrame;

public class MainPageViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new MainPageFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Main Page");
      frame.setVisible(true);      
   }
}
