package com.jkmalan.adoptafriend.interfaces.viewers;

import com.jkmalan.adoptafriend.interfaces.HomePage;

import javax.swing.*;

public class MainPageViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new HomePage(0);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setTitle("Main Page");
      frame.setVisible(true);      
   }
}
