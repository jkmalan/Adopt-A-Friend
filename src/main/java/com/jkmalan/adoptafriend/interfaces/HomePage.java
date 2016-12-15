package com.jkmalan.adoptafriend.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomePage extends JFrame {

    private static final int FRAME_WIDTH = 320;
    private static final int FRAME_HEIGHT = 480;

    private final int uid;

    private JPanel homePanel;

    public HomePage(int uid) {
        this.uid = uid;

        setJMenuBar(buildMenuBar());
        buildHomePanel();

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
                JFrame frame = new EditProfilePage(uid);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
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
                JFrame frame = new LoginPage();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                dispose();
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
                JFrame frame = new UserListingPage(uid);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
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
                JFrame frame = new CreateListingPage(uid);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
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
                JFrame frame = new SearchPage(uid);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        };
        listingSearchItem.addActionListener(listener);
        return listingSearchItem;
    }

    private void buildHomePanel() {

    }

}


