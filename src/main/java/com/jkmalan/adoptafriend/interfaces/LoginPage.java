package com.jkmalan.adoptafriend.interfaces;

import com.jkmalan.adoptafriend.AppEngine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPage extends JFrame {

    private static final int FRAME_WIDTH = 480;
    private static final int FRAME_HEIGHT = 720;

    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton createButton;

    private JPanel loginPanel;

    public LoginPage() {
        buildUsernameField();
        buildPasswordField();

        buildUserButton();
        buildCreateButton();

        buildLoginPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void buildUsernameField() {
        usernameLabel = new JLabel("UserName: ");
        usernameField = new JTextField();

    }

    private void buildPasswordField() {
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
    }

    private void buildUserButton() {
        loginButton = new JButton("Login");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                int result = AppEngine.getUserManager().validateUser(username, password);
                if (result != -1) {
                    JFrame frame = new HomePage(result);
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(loginPanel, "Incorrect username or password!");
                }
            }
        };
        loginButton.addActionListener(listener);
    }

    private void buildCreateButton() {
        createButton = new JButton("Create");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateProfilePage();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                dispose();
            }
        };
        createButton.addActionListener(listener);
    }

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

        add(loginPanel);
    }

}

