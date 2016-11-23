package com.jkmalan.adoptafriend;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPageFrame extends JFrame
{
	private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;
	  
    
    
    public MainPageFrame()
    {
    	JMenuBar menuBar = new JMenuBar();     
        setJMenuBar(menuBar);
        menuBar.add(createEditProfileMenu());
        menuBar.add(createSearchMenu());
    	menuBar.add(createCreateListingMenu());
    	menuBar.add(createViewListingMenu());
    	
    	JLabel label = new JLabel("WELCOME!");
    	add(label, BorderLayout.NORTH);
    	
    	setSize(FRAME_WIDTH, FRAME_HEIGHT);
    	
    }

	private JMenu createEditProfileMenu() 
	{
		JMenu menu = new JMenu("Edit Profile");
	      return menu;
	}



	private JMenu createSearchMenu() 
	{
		JMenu menu = new JMenu("Search");
	      return menu;
	}



	private JMenu createCreateListingMenu() 
	{
		JMenu menu = new JMenu("Create new Listing");
	      return menu;
	}



	private JMenu createViewListingMenu() 
	{
		JMenu menu = new JMenu("View Listings");
	      return menu;
	}

}


