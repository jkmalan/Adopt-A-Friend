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
package com.jkmalan.adoptafriend.client.gui;

import com.jkmalan.adoptafriend.client.ClientEngine;
import com.jkmalan.adoptafriend.client.gui.profile.CreateProfileFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple login page
 *
 * User may login to profile or create a profile
 */
public class LoginFrame extends JFrame {

    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton createButton;

    private JPanel loginPanel;

    public LoginFrame() {
        setSize(ClientEngine.FRAME_WIDTH, ClientEngine.FRAME_HEIGHT);
        setTitle("Login");

        buildUsernameField();
        buildPasswordField();
        buildLoginButton();
        buildCreateButton();
        buildLoginPanel();

        add(loginPanel);
    }

    /*
     * Initializes the username label and field
     */
    private void buildUsernameField() {
        usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
    }

    /*
     * Initializes the password label and field
     */
    private void buildPasswordField() {
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
    }

    /*
     * Initializes the login button and validates credentials
     */
    private void buildLoginButton() {
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                /*
                 * COMMAND: USER-VALIDATE username password
                 * Send username, password
                 * Receive UID
                 */
                int uid = -1;
                String data = ClientEngine.getConnection().exchangeData("USER-VALIDATE " + username + " " + new String(password));
                String[] args = data.split(" ");
                if (args[0].equals("DATA")) {
                    uid = Integer.parseInt(args[1]);
                }

                if (uid > -1) {
                    JFrame frame = new HomeFrame(uid);
                    ClientEngine.switchFrame(LoginFrame.this, frame);
                } else {
                    JOptionPane.showMessageDialog(loginPanel, "Incorrect username and/or password!");
                }
            }

        });
    }

    /*
     * Initializes the create profile button
     */
    private void buildCreateButton() {
        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateProfileFrame();
                ClientEngine.switchFrame(LoginFrame.this, frame);
            }

        });
    }

    /*
     * Constructs the login panel for the frame
     *
     * GridBayLayout used for a quick and dirty layout
     */
    private void buildLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints label = new GridBagConstraints();
        label.fill = GridBagConstraints.HORIZONTAL;
        label.insets = new Insets(20, 30, 0, 30);
        label.weightx = 0.5;
        label.gridwidth = 1;
        GridBagConstraints field = new GridBagConstraints();
        field.fill = GridBagConstraints.HORIZONTAL;
        field.insets = new Insets(0, 30, 0, 30);
        field.weightx = 0.5;
        field.gridwidth = 3;
        field.ipadx = 10;
        field.ipady = 20;
        GridBagConstraints button = new GridBagConstraints();
        button.fill = GridBagConstraints.HORIZONTAL;
        button.insets = new Insets(20, 30, 0, 30);
        button.weightx = 0.5;
        button.gridwidth = 1;

        label.gridx = 0;
        label.gridy = 0;
        loginPanel.add(usernameLabel, label);
        field.gridx = 0;
        field.gridy = 1;
        loginPanel.add(usernameField, field);
        label.gridx = 0;
        label.gridy = 2;
        loginPanel.add(passwordLabel, label);
        field.gridx = 0;
        field.gridy = 3;
        loginPanel.add(passwordField, field);
        button.gridx = 0;
        button.gridy = 4;
        loginPanel.add(loginButton, button);
        button.gridx = 2;
        button.gridy = 4;
        loginPanel.add(createButton, button);
    }

}
