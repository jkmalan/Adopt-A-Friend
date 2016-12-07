package com.jkmalan.adoptafriend.interfaces;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserListingFrame extends JFrame{
	
	
	
	private JLabel Photo1;
	private JLabel Photo2;
    private JButton EditListingButton;
    private JButton HomeButton;
    private JButton DeleteButton;
    private JButton CancelButton;
    private JPanel ListingPanel1;
    private JPanel ListingPanel2;
    private JPanel UserPanel;
    private JPanel PhotoPanel;
    private JPanel PhotoPanel2;
  
    private JPanel UserListingPanel;
    private ActionListener listener;
    
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
   
	public UserListingFrame()
	{
		/*PhotoPanel= new JPanel();
		Photo1 = new JLabel();
		PhotoPanel2= new JPanel();
		Photo1 = new JLabel();
		PhotoPanel.setLayout(new BorderLayout());
		PhotoPanel.add(Photo1, BorderLayout.CENTER);
		PhotoPanel2.setLayout(new BorderLayout());
		PhotoPanel2.add(Photo2, BorderLayout.CENTER);*/
		
		createEditListingButton();
		createDeleteButton();
		createHomeButton();
		
		ListingPanel1=new JPanel();
		//ListingPanel1.add(PhotoPanel);
		ListingPanel1.add(EditListingButton);
		ListingPanel1.add(DeleteButton);
		
		ListingPanel2= new JPanel();
		//ListingPanel2.add(PhotoPanel2);
		ListingPanel2.add(EditListingButton);
		ListingPanel2.add(DeleteButton);
		
		UserListingPanel=new JPanel();
		UserListingPanel.add(ListingPanel1);
		UserListingPanel.add(ListingPanel2);
		UserListingPanel.add(HomeButton);
		add(UserListingPanel);
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
	}
	
		
	private void createEditListingButton()
	{
		EditListingButton= new JButton("Edit Current Listing");
	
		class AddEditListener implements ActionListener
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	        	JFrame frame = new EditListingFrame();
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      frame.setTitle("Edit Listing");
		      frame.setVisible(true);   
	         }   
	      }
	         ActionListener listener = new AddEditListener();
		      EditListingButton.addActionListener(listener);
		
	}
	private void createHomeButton()
	{
		HomeButton= new JButton("Home");
		class AddHomeListener implements ActionListener
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 JFrame frame = new UserListingFrame();
	             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             frame.setTitle("Current Listings: ");
	             frame.setVisible(true);
	         }     
		
	}
		 ActionListener listener = new AddHomeListener();
	      HomeButton.addActionListener(listener);

}
	public void createDeleteButton()
	{
		DeleteButton= new JButton("Delete");
		class AddDeleteListener implements ActionListener
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 JFrame frame = new UserListingFrame();
	             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             frame.setTitle("Current Listings: ");
	             frame.setVisible(true);
	         }     
		
	}
		 ActionListener listener = new AddDeleteListener();
	      HomeButton.addActionListener(listener);

	}
}