package com.jkmalan.adoptafriend.interfaces;

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.listing.Listing;
import com.jkmalan.adoptafriend.user.User;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class UserListingPage extends JFrame {

    private static final int FRAME_WIDTH = 480;
    private static final int FRAME_HEIGHT = 720;

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

    private JScrollPane listingScroller;
    private JPanel userListingPanel;

    private User user;
    private List<Listing> listings;

    public UserListingPage(int uid) {
        user = AppEngine.getUserManager().getUser(uid);
        listings = AppEngine.getListingManager().getListings(uid);

        buildUserListingPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

    }

    public JPanel buildListingPanel(Listing listing) {
        JPanel listingPanel = new JPanel();
        JLabel title = new JLabel(listing.getTitle());
        JLabel photo = new JLabel();
        ImageIcon icon = new ImageIcon(listing.getPhoto().getPath());
        photo.setIcon(new ImageIcon(icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        JTextArea desc = new JTextArea(listing.getDesc());
        desc.setEditable(false);
        listingPanel.add(title);
        listingPanel.add(photo);
        listingPanel.add(desc);
        return listingPanel;
    }

    private void createEditListingListener() {

        class AddEditListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new EditListingPage();
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
                JFrame frame = new EditListingPage();
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
                JFrame frame = new UserListingPage(user.getUserID());
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
                JFrame frame = new UserListingPage(user.getUserID());
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
                JFrame frame = new UserListingPage(user.getUserID());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Current Listings: ");
                frame.setVisible(true);
            }

        }
        ActionListener listener = new AddDeleteListener();
        DeleteButton2.addActionListener(listener);

    }

    private void buildUserListingPanel() {
        userListingPanel = new JPanel();

        for (Listing l : listings) {
            userListingPanel.add(buildListingPanel(l));
        }

        // TODO Scrollbar...
        add(userListingPanel);
    }

}