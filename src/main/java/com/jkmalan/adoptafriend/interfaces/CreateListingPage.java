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
import com.jkmalan.adoptafriend.user.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class CreateListingPage extends JFrame {

    private static final int FRAME_WIDTH = 480;
    private static final int FRAME_HEIGHT = 720;

    private JLabel titleLabel;
    private JLabel zipLabel;
    private JLabel typeLabel;
    private JLabel sexLabel;
    private JLabel ageLabel;
    private JLabel descLabel;
    private JLabel photoLabel;

    private JTextField titleField;
    private JTextField zipField;
    private JTextField typeField;
    private JTextField sexField;
    private JTextField ageField;
    private JTextArea descArea;
    private JLabel photoBox;

    private JButton photoButton;
    private JButton createButton;
    private JButton cancelButton;

    private JPanel listingPanel;

    private User user;
    private File filePhoto = null;

    public CreateListingPage(int uid) {
        user = ServerEngine.getUserManager().getUser(uid);

        buildListingFields();

        buildPhotoButton();
        buildSaveButton();
        buildCancelButton();

        buildListingPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void buildListingFields() {
        titleLabel = new JLabel("Title: ");
        titleField = new JTextField(10);
        zipLabel = new JLabel("Zip: ");
        zipField = new JTextField(10);
        typeLabel = new JLabel("Type: ");
        typeField = new JTextField(10);
        sexLabel = new JLabel("Sex: ");
        sexField = new JTextField(10);
        ageLabel = new JLabel("Age: ");
        ageField = new JTextField(10);
        descLabel = new JLabel("Description: ");
        descArea = new JTextArea(5, 20);
        descArea.setEditable(true);
        photoLabel = new JLabel("Photo: ");
        photoBox = new JLabel();
    }

    private void buildPhotoButton() {
        photoButton = new JButton("Open File");
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    String fileName = file.getAbsolutePath();
                    ImageIcon icon = new ImageIcon(fileName);
                    Image image = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                    photoBox.setIcon(new ImageIcon(image));
                    listingPanel.revalidate();
                    listingPanel.repaint();
                    filePhoto = file;
                }
            }
        };
        photoButton.addActionListener(listener);
    }


    private void buildSaveButton() {
        createButton = new JButton("Create");
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String zip = zipField.getText();
                String type = typeField.getText();
                String sex = sexField.getText();
                int age = Integer.valueOf(ageField.getText());
                String desc = descArea.getText();

                ServerEngine.getListingManager().createListing(user.getUserID(), title, zip, type, sex, age, desc, filePhoto);

                // TODO Close the create listing screen, open the user's listings screen
            }
        };
        createButton.addActionListener(listener);
    }

    private void buildCancelButton() {
        cancelButton = new JButton("Cancel");
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        cancelButton.addActionListener(listener);
    }

    private void buildListingPanel() {
        listingPanel = new JPanel();

        listingPanel.add(titleLabel);
        listingPanel.add(titleField);
        listingPanel.add(zipLabel);
        listingPanel.add(zipField);
        listingPanel.add(typeLabel);
        listingPanel.add(typeField);
        listingPanel.add(ageLabel);
        listingPanel.add(ageField);
        listingPanel.add(descLabel);
        listingPanel.add(descArea);
        listingPanel.add(photoLabel);
        listingPanel.add(photoBox);

        listingPanel.add(photoButton);
        listingPanel.add(createButton);
        listingPanel.add(cancelButton);

        add(listingPanel);
    }

}
