package com.jkmalan.adoptafriend.interfaces;

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class SearchPage extends JFrame {

    private static final int FRAME_WIDTH = 480;
    private static final int FRAME_HEIGHT = 720;

    private JLabel titleLabel;
    private JLabel zipLabel;
    private JLabel typeLabel;
    private JLabel sexLabel;
    private JLabel ageLabel;

    private JTextField titleField;
    private JFormattedTextField zipField;
    private JTextField typeField;
    private JFormattedTextField sexField;
    private JFormattedTextField ageField;

    private JButton searchButton;

    private JPanel searchPanel;

    private User user;

    public SearchPage(int uid) {
        user = AppEngine.getDatabaseManager().selectUser(uid);

        buildSearchPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void buildSearchFields() {
        titleLabel = new JLabel("Title: ");
        titleField = new JTextField();
        zipLabel = new JLabel("Zip (5 digit zip code): ");
        zipField = new JFormattedTextField();
        typeLabel = new JLabel("Type: ");
        typeField = new JTextField();
        sexLabel = new JLabel("Sex (M/F):");
        sexField = new JFormattedTextField();
        ageLabel = new JLabel("Age (Integers only):");
        ageField = new JFormattedTextField();
    }

    private void buildSearchButton() {
        searchButton = new JButton("Search");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String zip = zipField.getText();
                String type = typeField.getText();
                String sex = sexField.getText();
                int age = Integer.parseInt(ageField.getText());

                List<Listing> listings = AppEngine.getListingManager().getListings(title, zip, type, sex, age);

                JFrame frame = new SearchListingPage(listings);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                dispose();
            }
        };
        searchButton.addActionListener(listener);
    }

    private void buildSearchPanel() {
        searchPanel = new JPanel();

        searchPanel.add(titleLabel);
        searchPanel.add(titleField);
        searchPanel.add(zipLabel);
        searchPanel.add(zipField);
        searchPanel.add(typeLabel);
        searchPanel.add(typeField);
        searchPanel.add(sexLabel);
        searchPanel.add(sexField);
        searchPanel.add(ageLabel);
        searchPanel.add(ageField);

        add(searchPanel);
    }

}
