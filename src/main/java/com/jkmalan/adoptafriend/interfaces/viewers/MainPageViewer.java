package com.jkmalan.adoptafriend.interfaces.viewers;

import com.jkmalan.adoptafriend.interfaces.MainPageFrame;

import javax.swing.*;

public class MainPageViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new MainPageFrame(0);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setTitle("Main Page");
      frame.setVisible(true);      
   }
}
