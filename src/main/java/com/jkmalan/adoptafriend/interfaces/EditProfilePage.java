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

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.user.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class EditProfilePage extends JFrame {

    private static final int FRAME_WIDTH = 480;
    private static final int FRAME_HEIGHT = 720;
    private static final int FIELD_WIDTH = 15;

    private JLabel usernameLabel;
    private JLabel firstnameLabel;
    private JLabel lastnameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JLabel zipLabel;
    private JLabel descLabel;
    private JLabel photoLabel;
    private JLabel photoBox;
    private JLabel passLabel;
    private JLabel passConfirmLabel;

    private JTextField usernameField;
    private JTextField firstnameField;
    private JTextField lastnameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextArea descArea;
    private JPasswordField passField;
    private JPasswordField passConfirmField;

    private JButton photoButton;
    private JButton updateButton;

    private JPanel profilePanel;

    private User user;
    private File filePhoto;

    public EditProfilePage(int uid) {
        user = AppEngine.getUserManager().getUser(uid);
        filePhoto = user.getPhoto();

        buildProfileFields();

        buildPhotoButton();
        buildUpdateButton();

        buildProfilePanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void buildProfileFields() {
        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(user.getUserName(), FIELD_WIDTH);
        usernameField.setEnabled(false);
        emailLabel = new JLabel("Email: ");
        emailField = new JTextField(user.getEmail(), FIELD_WIDTH);
        passLabel = new JLabel("Password: ");
        passField = new JPasswordField(FIELD_WIDTH);
        passConfirmLabel = new JLabel("Confirm Password: ");
        passConfirmField = new JPasswordField(FIELD_WIDTH);
        firstnameLabel = new JLabel("First Name: ");
        firstnameField = new JTextField(user.getFirstName(), FIELD_WIDTH);
        lastnameLabel = new JLabel("Last Name: ");
        lastnameField = new JTextField(user.getLastName(), FIELD_WIDTH);
        phoneLabel = new JLabel("Phone: ");
        phoneField = new JTextField(user.getPhone(), FIELD_WIDTH);
        streetLabel = new JLabel("Street: ");
        streetField = new JTextField(user.getStreet(), FIELD_WIDTH);
        cityLabel = new JLabel("City: ");
        cityField = new JTextField(user.getCity(), FIELD_WIDTH);
        stateLabel = new JLabel("State: ");
        stateField = new JTextField(user.getState(), FIELD_WIDTH);
        zipLabel = new JLabel("Zip: ");
        zipField = new JTextField(user.getZip(), FIELD_WIDTH);
        descLabel = new JLabel("Description: ");
        descArea = new JTextArea(user.getDesc(), 5, 20);
        descArea.setEditable(true);
        photoLabel = new JLabel("Photo: ");
        photoBox = new JLabel();
        ImageIcon icon = new ImageIcon(user.getPhoto().getPath());
        photoBox.setIcon(new ImageIcon(icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
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
                    profilePanel.revalidate();
                    profilePanel.repaint();
                    filePhoto = file;
                }
            }
        };
        photoButton.addActionListener(listener);
    }


    private void buildUpdateButton() {
        updateButton = new JButton("Save");
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                char[] password = passField.getPassword();
                String username = usernameField.getText();
                String firstName = firstnameField.getText();
                String lastName = lastnameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String street = streetField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String zip = zipField.getText();
                String desc = descArea.getText();

                AppEngine.getUserManager().modifyUser(user.getUserID(), username, password, firstName, lastName, email, phone, street, city, state, zip, desc, filePhoto);
            }
        };
        updateButton.addActionListener(listener);
    }

    private void buildProfilePanel() {
        profilePanel = new JPanel();

        profilePanel.add(usernameLabel);
        profilePanel.add(usernameField);
        profilePanel.add(emailLabel);
        profilePanel.add(emailField);
        profilePanel.add(passLabel);
        profilePanel.add(passField);
        profilePanel.add(passConfirmLabel);
        profilePanel.add(passConfirmField);

        profilePanel.add(firstnameLabel);
        profilePanel.add(firstnameField);
        profilePanel.add(lastnameLabel);
        profilePanel.add(lastnameField);
        profilePanel.add(phoneLabel);
        profilePanel.add(phoneField);
        profilePanel.add(streetLabel);
        profilePanel.add(streetField);
        profilePanel.add(cityLabel);
        profilePanel.add(cityField);
        profilePanel.add(stateLabel);
        profilePanel.add(stateField);
        profilePanel.add(zipLabel);
        profilePanel.add(zipField);

        profilePanel.add(photoLabel);
        profilePanel.add(photoBox);
        profilePanel.add(photoButton);
        profilePanel.add(descLabel);
        profilePanel.add(descArea);

        profilePanel.add(updateButton);

        add(profilePanel);
    }

}



