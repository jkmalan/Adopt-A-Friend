package com.jkmalan.adoptafriend.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame
{

	private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;
	   
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JTextField loginField;
    private JTextField passwordField;
    private JButton SignInButton;
    private JButton ClickHereButton;
    
    public LoginFrame()
    {
    	
    	createLoginField();
    	createPasswordField();
    	createSignInButton();
    	createClickHereButton();
    	createPanel();
    	
    	setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

	private void createLoginField()
	{
		loginLabel = new JLabel("UserName: ");
		final int FIELD_WIDTH = 15;
	    loginField = new JTextField(FIELD_WIDTH);
		
	}
	
	private void createPasswordField() 
	{
		passwordLabel = new JLabel("Password: ");
		final int FIELD_WIDTH = 15;
	    passwordField = new JTextField(FIELD_WIDTH);
	}

	private void createSignInButton()
	{
		SignInButton = new JButton("Sign In");
		
	}

	private void createClickHereButton() 
	{
		ClickHereButton = new JButton("Click Here");
		
	}
	
	private void createPanel()
	{
		JPanel panel = new JPanel();
	    panel.add(loginLabel);
	    panel.add(loginField);	  
	    panel.add(passwordLabel);
	    panel.add(passwordField);
	    panel.add(SignInButton);
	    JLabel label = new JLabel("Don't have an account? ");
	    panel.add(label);
	    panel.add(ClickHereButton);
	    add(panel);
		
	}
	
}

