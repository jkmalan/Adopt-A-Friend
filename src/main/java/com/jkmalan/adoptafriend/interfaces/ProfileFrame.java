package com.jkmalan.adoptafriend.interfaces;

import com.jkmalan.adoptafriend.AppEngine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class ProfileFrame extends JFrame {

    private static final int FRAME_WIDTH = 320;
    private static final int FRAME_HEIGHT = 480;

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
    private JButton createButton;
    private JButton cancelButton;

    private JPanel profilePanel;

    public ProfileFrame() {
        buildAccountFields();
        buildProfileFields();
        buildInfoFields();

        buildPhotoButton();
        buildSaveButton();
        buildCancelButton();

        buildProfilePanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    private void buildAccountFields() {
        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(10);
        emailLabel = new JLabel("Email: ");
        emailField = new JTextField(10);
        passLabel = new JLabel("Password: ");
        passField = new JPasswordField(10);
        passConfirmLabel = new JLabel("Confirm Password: ");
        passConfirmField = new JPasswordField(10);
    }

    private void buildProfileFields() {
        firstnameLabel = new JLabel("First Name: ");
        firstnameField = new JTextField(10);
        lastnameLabel = new JLabel("Last Name: ");
        lastnameField = new JTextField(10);
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

    private void buildInfoFields() {
        descLabel = new JLabel("Description: ");
        descArea = new JTextArea(10, 20);
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
                    profilePanel.revalidate();
                    profilePanel.repaint();
                }
            }
        };
        photoButton.addActionListener(listener);
    }


    private void buildSaveButton() {
        createButton = new JButton("Create");
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passField.getPassword());
                String firstName = firstnameField.getText();
                String lastName = lastnameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String street = streetField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String zip = zipField.getText();
                String desc = descArea.getText();
                String photo = photoBox.getText();
                AppEngine.getUserManager().createUser(username, password, firstName, lastName, email, phone, street, city, state, zip, desc, photo);
                int result = AppEngine.getUserManager().validateUser(username, password);

                JFrame frame = new MainPageFrame(result);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setTitle("Main Page");
                frame.setVisible(true);
                dispose();
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


    private void buildProfilePanel() {
        profilePanel = new JPanel();
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

        profilePanel.add(createButton);
        profilePanel.add(cancelButton);

        add(profilePanel);
    }

}



