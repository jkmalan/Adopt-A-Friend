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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author jkmalan (aka John Malandrakis)
 */
public class LoginPanel extends JPanel {

    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton createButton;

    public LoginPanel() {
        buildComponents();

        addComponents();
    }

    private void addComponents() {
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);

        add(loginButton);
        add(createButton);
    }

    private void buildComponents() {
        buildUsernameField();
        buildPasswordField();
        buildLoginButton();
        buildCreateButton();
    }

    private void buildUsernameField() {
        usernameLabel = new JLabel("UserName: ");
        usernameField = new JTextField();
    }

    private void buildPasswordField() {
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
    }

    private void buildLoginButton() {
        loginButton = new JButton("Sign In");
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager.getPanelManager().showLoginPanel(false);
                PanelManager.getPanelManager().showHomePanel(true);
            }

        };
        loginButton.addActionListener(listener);
    }

    private void buildCreateButton() {
        createButton = new JButton("Create Account");
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
        createButton.addActionListener(listener);
    }

}