package com.jkmalan.adoptafriend.interfaces;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EditProfileFrame extends JFrame
{
	private static final int FRAME_WIDTH = 940;
    private static final int FRAME_HEIGHT = 400;
    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 25;
	private static final int FIELD_WIDTH = 20;
	
    private JLabel FirstNameLabel;
    private JLabel LastNameLabel;
    private JLabel StreetLabel;
    private JLabel StateLabel;
    private JLabel ZipCodeLabel;
    private JLabel PhoneNumberLabel;
    private JLabel EmailLabel;
    private JLabel DescriptionLabel;
    private JLabel AddPhotoLabel;
    private JLabel Image;
    private JTextField FirstNameField;
    private JTextField LastNameField;
    private JTextField StreetField;
    private JTextField StateField;
    private JTextField ZipCodeField;
    private JTextField PhoneNumberField;
    private JTextField EmailField;
    private JTextArea DescriptionArea;
    private JButton UpdateButton;
    private JButton CancelButton;
    private JButton OpenFileButton;
    private JPanel jpanel, panel;
    
    public EditProfileFrame()
    {
    	AddPhotoLabel = new JLabel("Add Photo:* ");
    	FirstNameLabel = new JLabel("First Name:* ");
    	LastNameLabel = new JLabel("Last Name:* ");
    	StreetLabel = new JLabel("Street:* ");
    	StateLabel = new JLabel("State:* ");
    	ZipCodeLabel = new JLabel("Zip Code:* ");
    	PhoneNumberLabel = new JLabel("Phone Number:* ");
    	EmailLabel = new JLabel("Email:* ");
    	DescriptionLabel = new JLabel("Description: ");
    	FirstNameField = new JTextField(FIELD_WIDTH);
    	LastNameField = new JTextField(FIELD_WIDTH);
    	StreetField = new JTextField(FIELD_WIDTH);
    	StateField = new JTextField(FIELD_WIDTH);
    	ZipCodeField = new JTextField(FIELD_WIDTH);
    	PhoneNumberField = new JTextField(FIELD_WIDTH);
    	EmailField = new JTextField(FIELD_WIDTH);
    	DescriptionArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        DescriptionArea.setEditable(true);
        
        jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        Image = new JLabel(" ");
        jpanel.add(Image, BorderLayout.CENTER);
        
       createOpenFileButton(); 
       createUpdateButton();
       createCancelButton();
       createProfilePanel();
       
       setSize(FRAME_WIDTH, FRAME_HEIGHT);
       
    }

    private void createOpenFileButton()
	{
		OpenFileButton = new JButton("Open File");
		
		OpenFileButton.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent ae) 
            {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) 
                	{
	                    File file = fc.getSelectedFile();
	                    String sname = file.getAbsolutePath(); 
	                    Image = new JLabel("", new ImageIcon(sname), JLabel.CENTER);
	                    jpanel.add(Image, BorderLayout.CENTER);
	                    jpanel.revalidate(); 
	                    jpanel.repaint(); 
                	}
            }
        });
		
	}
    
    
	private void createUpdateButton() 
	{
		UpdateButton = new JButton("Update");
		class AddUpdateListener implements ActionListener
	      {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 JFrame frame = new MainPageFrame();
	             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             frame.setTitle("Main Page");
	             frame.setVisible(true);  
	      
	         }            
	      }
	      
	      ActionListener listener = new AddUpdateListener();
	      UpdateButton.addActionListener(listener);
	}
		
	

	private void createCancelButton() 
	{
		CancelButton = new JButton("Cancel");
		
		class AddCancelListener implements ActionListener
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	System.exit(0);
		    }
		}
	ActionListener listener = new AddCancelListener();
    CancelButton.addActionListener(listener);
    
	}


	private void createProfilePanel() 
	{
		panel = new JPanel();
		panel.add(AddPhotoLabel);
		panel.add(OpenFileButton);
		panel.add(jpanel);
		panel.add(FirstNameLabel);
		panel.add(FirstNameField);
		panel.add(LastNameLabel);
		panel.add(LastNameField);
		panel.add(StreetLabel);
		panel.add(StreetField);
		panel.add(StateLabel);
		panel.add(StateField);
		panel.add(ZipCodeLabel);
		panel.add(ZipCodeField);
		panel.add(PhoneNumberLabel);
		panel.add(PhoneNumberField);
		panel.add(EmailLabel);
		panel.add(EmailField);
		panel.add(DescriptionLabel);
		panel.add(DescriptionArea);
		panel.add(UpdateButton);
		panel.add(CancelButton);
		add(panel);	
	}

}



