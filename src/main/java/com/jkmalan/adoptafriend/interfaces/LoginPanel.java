package com.jkmalan.adoptafriend.interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JLabel titleLabel;
    private JLabel userLabel;
    private JLabel passLabel;

    private JTextField userField;
    private JPasswordField passField;

    private JButton loginButton;
    private JButton createButton;

    public LoginPanel() {
        buildTitleLabel();
        buildUserLabel();
        buildPassLabel();

        buildUserField();
        buildPassField();

        buildLoginButton();
        buildCreateButton();

        add(titleLabel);
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);
        add(createButton);
    }

    public void buildTitleLabel() {
        titleLabel = new JLabel("Login Page");
    }

    public void buildUserLabel() {
        userLabel = new JLabel("user");
    }

    public void buildPassLabel() {
        passLabel = new JLabel("pass");
    }

    public void buildUserField() {
        userField = new JTextField(15);
    }

    public void buildPassField() {
        passField = new JPasswordField(15);
    }

    public void buildLoginButton() {
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Validate login credentials
                // TODO Proceed to home page

            }

        });
    }

    public void buildCreateButton() {
        createButton = new JButton("Create");

        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Proceed to create page

            }

        });
    }

}
