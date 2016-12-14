package com.jkmalan.adoptafriend.interfaces;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserListingFrame extends JFrame {

    private JLabel Photo1;
    private JLabel Photo2;
    private JButton EditListingButton;
    private JButton EditListingButton2;
    private JButton HomeButton;
    private JButton DeleteButton2;
    private JButton DeleteButton;
    private JPanel ListingPanel;
    private JPanel UserPanel;

    private ActionListener listener;

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    public UserListingFrame() {
        ListingPanel = new JPanel();
        ListingPanel.setLayout(new GridLayout(2, 3));
        Photo1 = new JLabel(" ");
        Photo2 = new JLabel(" ");


        EditListingButton = new JButton("Edit Current Listing");
        EditListingButton2 = new JButton("Edit Current Listing");

        DeleteButton = new JButton("Delete");
        DeleteButton2 = new JButton("Delete");
        HomeButton = new JButton("Home");


        createEditListingListener();
        createDeleteListener();

        createEditListingListener2();
        createDeleteListener2();
        createHomeListener();

        ListingPanel.add(Photo1);
        ListingPanel.add(EditListingButton);
        ListingPanel.add(DeleteButton);

        ListingPanel.add(Photo2);
        ListingPanel.add(EditListingButton2);
        ListingPanel.add(DeleteButton2);


        UserPanel = new JPanel();
        UserPanel.setLayout(new BorderLayout());
        UserPanel.add(ListingPanel, BorderLayout.CENTER);
        UserPanel.add(HomeButton, BorderLayout.SOUTH);
        add(UserPanel);


        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }


    private void createEditListingListener() {

        class AddEditListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new EditListingFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Edit Listing");
                frame.setVisible(true);
            }
        }
        ActionListener listener = new AddEditListener();
        EditListingButton.addActionListener(listener);

    }

    private void createEditListingListener2() {

        class AddEditListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new EditListingFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Edit Listing");
                frame.setVisible(true);
            }
        }
        ActionListener listener = new AddEditListener();
        EditListingButton2.addActionListener(listener);

    }

    private void createHomeListener() {

        class AddHomeListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new UserListingFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Current Listings: ");
                frame.setVisible(true);
            }

        }
        ActionListener listener = new AddHomeListener();
        HomeButton.addActionListener(listener);

    }

    public void createDeleteListener() {

        class AddDeleteListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new UserListingFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Current Listings: ");
                frame.setVisible(true);
            }

        }
        ActionListener listener = new AddDeleteListener();
        DeleteButton.addActionListener(listener);

    }

    public void createDeleteListener2() {

        class AddDeleteListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new UserListingFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Current Listings: ");
                frame.setVisible(true);
            }

        }
        ActionListener listener = new AddDeleteListener();
        DeleteButton2.addActionListener(listener);

    }
}