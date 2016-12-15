package com.jkmalan.adoptafriend.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomePage extends JFrame {

    private static final int FRAME_WIDTH = 320;
    private static final int FRAME_HEIGHT = 480;

    private final int uid;

    private JPanel mainPanel;

    public HomePage(int uid) {
        this.uid = uid;

        setJMenuBar(buildMenuBar());
        buildMainPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(buildProfileMenu());
        menuBar.add(buildListingMenu());
        return menuBar;
    }

    private JMenu buildProfileMenu() {
        JMenu profileMenu = new JMenu("Profile");
        profileMenu.add(buildProfileEditItem());
        profileMenu.add(buildProfileLogoutItem());
        return profileMenu;
    }

    private JMenuItem buildProfileEditItem() {
        JMenuItem profileEditItem = new JMenuItem("Edit");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Open the user's profile screen
            }
        };
        profileEditItem.addActionListener(listener);
        return profileEditItem;
    }

    private JMenuItem buildProfileLogoutItem() {
        JMenuItem profileLogoutItem = new JMenuItem("Logout");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Return to the login screen
            }
        };
        profileLogoutItem.addActionListener(listener);
        return profileLogoutItem;
    }

    private JMenu buildListingMenu() {
        JMenu listingMenu = new JMenu("Listing");
        listingMenu.add(buildListingViewItem());
        listingMenu.add(buildListingCreateItem());
        listingMenu.add(buildListingSearchItem());
        return listingMenu;
    }

    private JMenuItem buildListingViewItem() {
        JMenuItem listingViewItem = new JMenuItem("View");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Open the user's listings
            }
        };
        listingViewItem.addActionListener(listener);
        return listingViewItem;
    }

    private JMenuItem buildListingCreateItem() {
        JMenuItem listingCreateItem = new JMenuItem("Create");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Open the listing create screen
            }
        };
        listingCreateItem.addActionListener(listener);
        return listingCreateItem;
    }

    private JMenuItem buildListingSearchItem() {
        JMenuItem listingSearchItem = new JMenuItem("Search");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Open the listing search screen
            }
        };
        listingSearchItem.addActionListener(listener);
        return listingSearchItem;
    }

    private void buildMainPanel() {

    }

}


