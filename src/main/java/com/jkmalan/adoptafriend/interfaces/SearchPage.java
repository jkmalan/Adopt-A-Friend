package com.jkmalan.adoptafriend.interfaces;

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.user.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SearchPage extends JFrame {
    
    private JLabel ResultLabel;
  
    private JTextField ZipField;
    private JTextField TypeField;
    private JTextField SexField;
    private JTextField AgeField;
    private JLabel ZipLabel;
    private JLabel TypeLabel;
    private JLabel SexLabel;
    private JLabel AgeLabel;
    private JButton SearchButton;
    private JButton CancelButton;
    private JPanel SearchPanel;
    private JPanel ResultPanel;
    private ActionListener listener;

    private static final int FRAME_WIDTH = 320;
    private static final int FRAME_HEIGHT = 480;
    private static final int FIELD_WIDTH = 25;

    private User user;

    public SearchPage(int uid) {
        user = AppEngine.getDatabaseManager().selectUser(uid);

        ResultLabel = new JLabel("Results: ");
        createTextArea();
        createSearchButton();
        createSearchPanel();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createSearchPanel() {

        SearchPanel = new JPanel();
     
        SearchPanel.add(ZipLabel);
        SearchPanel.add(ZipField);
        SearchPanel.add(TypeLabel);
        SearchPanel.add(TypeField);
        SearchPanel.add(SexLabel);
        SearchPanel.add(SexField);
        SearchPanel.add(AgeLabel);
        SearchPanel.add(AgeField);
        
        SearchPanel.add(SearchButton);
        SearchPanel.add(ResultLabel);
        add(SearchPanel);


    }

    private void createSearchButton() {
        SearchButton = new JButton("Search");
        class AddSearchListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                ResultLabel.setText("Results: ");
                JFrame frame = new SelectedResultFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Selected Pet Lisitng");
                frame.setVisible(true);
            }
        }

        ActionListener listener = new AddSearchListener();
        SearchButton.addActionListener(listener);
    }

    private void createTextArea() {
        ZipLabel = new JLabel("Enter Zip Code: ");
        ZipField = new JTextField(FIELD_WIDTH);
        TypeLabel = new JLabel("Enter Type: ");
        TypeField = new JTextField(FIELD_WIDTH);
        SexLabel = new JLabel("Enter Sex of Animal: ");
        SexField = new JTextField(FIELD_WIDTH);
        AgeLabel = new JLabel("Enter Age: ");
        AgeField = new JTextField(FIELD_WIDTH);
        
        
    }


}
