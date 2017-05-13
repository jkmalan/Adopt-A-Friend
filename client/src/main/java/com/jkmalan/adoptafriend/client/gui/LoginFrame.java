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
                int uid = 0;
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
     */
    private void buildLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(createButton);
    }

}
