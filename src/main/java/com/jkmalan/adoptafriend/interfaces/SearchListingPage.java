package com.jkmalan.adoptafriend.interfaces;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SearchListingPage extends JFrame {

    private static final int FRAME_WIDTH = 480;
    private static final int FRAME_HEIGHT = 720;

    private JLabel photoLabel;
    private JLabel contactLabel;
    private JLabel ProfileLabel;
    private JPanel panel;
    private JPanel resultPanel;
    private JLabel Photo;
    private JButton homeButton;
    private JPanel p;

    public SearchListingPage() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Photo = new JLabel(" ");
        panel.add(Photo, BorderLayout.NORTH);

        photoLabel = new JLabel("Photo: ");
        ProfileLabel = new JLabel("Pet Profile: ");
        contactLabel = new JLabel("Contact Information : ");

        createHomeButton();
        createSelectedResultPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createSelectedResultPanel() {
        resultPanel = new JPanel(new GridLayout(5, 1));
        resultPanel.setBorder(new TitledBorder(new EtchedBorder(), "Selected Pet Information"));
        resultPanel.add(Photo);
        resultPanel.add(photoLabel);
        resultPanel.add(ProfileLabel);
        resultPanel.add(contactLabel);
        resultPanel.add(homeButton);
        /*
		p =new JPanel();
		p.setLayout(new BorderLayout());
		p.add(resultPanel, BorderLayout.CENTER);
		
		p.add(homeButton, BorderLayout.SOUTH);*/
        add(resultPanel);


    }

    private void createHomeButton() {
        homeButton = new JButton("Home");
        class AddHomeListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {

                JFrame frame = new HomePage(0);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Main Page");
                frame.setVisible(true);

            }
        }

        ActionListener listener = new AddHomeListener();
        homeButton.addActionListener(listener);
    }

}
	
	

