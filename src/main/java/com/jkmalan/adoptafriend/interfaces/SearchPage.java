/*
* This file is part of Adopt-A-Friend, licensed under the MIT License (MIT).
*
* Copyright (c) Adopt-A-Friend
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/
package com.jkmalan.adoptafriend.interfaces;

import com.jkmalan.adoptafriend.ServerEngine;
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
        user = ServerEngine.getDatabaseManager().selectUser(uid);

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

                List<Listing> listings = ServerEngine.getListingManager().getListings(title, zip, type, sex, age);

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
