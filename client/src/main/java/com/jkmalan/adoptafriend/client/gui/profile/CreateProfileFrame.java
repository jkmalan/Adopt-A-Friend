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
package com.jkmalan.adoptafriend.client.gui.profile;

import com.jkmalan.adoptafriend.client.ClientEngine;
import com.jkmalan.adoptafriend.client.gui.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * A simple profile creation page
 *
 * User may input personal data to create a profile
 */
public class CreateProfileFrame extends JFrame {

    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmLabel;
    private JPasswordField confirmField;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel streetLabel;
    private JTextField streetField;
    private JLabel cityLabel;
    private JTextField cityField;
    private JLabel stateLabel;
    private JTextField stateField;
    private JLabel zipLabel;
    private JTextField zipField;

    private JButton createButton;
    private JButton cancelButton;

    private JPanel profilePanel;

    public CreateProfileFrame() {
        setSize(ClientEngine.FRAME_WIDTH, ClientEngine.FRAME_HEIGHT);
        setTitle("Create Profile");

        buildUsernameField();
        buildPasswordField();
        buildContactField();
        buildCreateButton();
        buildCancelButton();
        buildProfilePanel();

        add(profilePanel);
    }

    public void buildUsernameField() {
        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(10);
    }

    public void buildPasswordField() {
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField(10);
        confirmLabel = new JLabel("Confirm Password: ");
        confirmField = new JPasswordField(10);
    }

    public void buildContactField() {
        firstNameLabel = new JLabel("First Name: ");
        firstNameField = new JTextField(10);
        lastNameLabel = new JLabel("Last Name: ");
        lastNameField = new JTextField(10);
        emailLabel = new JLabel("Email: ");
        emailField = new JTextField(10);
        phoneLabel = new JLabel("Phone: ");
        phoneField = new JTextField(10);
        streetLabel = new JLabel("Street: ");
        streetField = new JTextField(10);
        cityLabel = new JLabel("City: ");
        cityField = new JTextField(10);
        stateLabel = new JLabel("State: ");
        stateField = new JTextField(10);
        zipLabel = new JLabel("Zip: ");
        zipField = new JTextField(10);
    }

    public void buildCreateButton() {
        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                char[] confirm = confirmField.getPassword();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String street = streetField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String zip = zipField.getText();

                String message = "";
                if (username == null || username.length() <= 3) {
                    message += "Username must be greater than 3 characters!\n";
                }
                if (!Arrays.equals(password, confirm)) {
                    message += "Password fields do not match!\n";
                }
                if (firstName == null || firstName.isEmpty()) {
                    message += "First must be filled in!\n";
                }
                if (lastName == null || lastName.isEmpty()) {
                    message += "Last name must be filled in!\n";
                }
                if (email == null || email.isEmpty() || !email.contains("@")) {
                    message += "Email must be filled in!\n";
                }
                if (phone == null || phone.isEmpty() || phone.length() != 10) {
                    message += "Phone number must be filled in!\n";
                }
                if (street == null || street.isEmpty()) {
                    message += "Street must be filled in!\n";
                }
                if (city == null || city.isEmpty()) {
                    message += "City must be filled in!\n";
                }
                if (state == null || state.isEmpty()) {
                    message += "State must be filled in!\n";
                }
                if (zip == null || zip.isEmpty() || zip.length() != 5) {
                    message += "Zip must be filled in!";
                }

                if (message.length() > 1) {
                    JOptionPane.showMessageDialog(profilePanel, message, "Incorrect inputs!", JOptionPane.ERROR_MESSAGE);
                } else {
                    ClientEngine.getConnection().exchangeData("USER-CREATE " + username + " " +
                    new String(password) + " " + firstName + " " + lastName + " " + email + " " + phone + " " +
                    street + " " + city + " " + state + " " + zip);

                    JFrame frame = new LoginFrame();
                    ClientEngine.switchFrame(CreateProfileFrame.this, frame);
                }
            }
        });
    }

    public void buildCancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new LoginFrame();
                ClientEngine.switchFrame(CreateProfileFrame.this, frame);
            }
        });
    }

    public void buildProfilePanel() {
        profilePanel = new JPanel();
        profilePanel.add(usernameLabel);
        profilePanel.add(usernameField);
        profilePanel.add(passwordLabel);
        profilePanel.add(passwordField);
        profilePanel.add(confirmLabel);
        profilePanel.add(confirmField);
        profilePanel.add(emailLabel);
        profilePanel.add(emailField);
        profilePanel.add(firstNameLabel);
        profilePanel.add(firstNameField);
        profilePanel.add(lastNameLabel);
        profilePanel.add(lastNameField);
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
        profilePanel.add(createButton);
        profilePanel.add(cancelButton);
    }

}
