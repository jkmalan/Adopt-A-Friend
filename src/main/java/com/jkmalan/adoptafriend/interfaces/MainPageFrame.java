package com.jkmalan.adoptafriend.interfaces;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainPageFrame extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;


    public MainPageFrame(int uid) {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(createEditProfileMenu());
        menuBar.add(createSearchMenu());
        menuBar.add(createListingMenu());

        JLabel label = new JLabel("WELCOME!");
        add(label, BorderLayout.NORTH);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    private JMenu createEditProfileMenu() {
        JMenu menu = new JMenu("Edit your Profile");
        menu.add(createEditItem());
        return menu;
    }

    private JMenuItem createEditItem() {
        JMenuItem item = new JMenuItem("Edit");
        class MenuItemListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new EditProfileFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Edit your account");
                frame.setVisible(true);
            }
        }
        ActionListener listener = new MenuItemListener();
        item.addActionListener(listener);
        return item;
    }

    private JMenu createSearchMenu() {
        JMenu menu = new JMenu("Search");
        menu.add(createSearchItem());
        return menu;
    }

    private JMenuItem createSearchItem() {
        JMenuItem item = new JMenuItem("Search");
        class MenuItemListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new SearchFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Search");
                frame.setVisible(true);
            }
        }
        ActionListener listener = new MenuItemListener();
        item.addActionListener(listener);
        return item;
    }

    private JMenu createListingMenu() {
        JMenu menu = new JMenu("Listing");
        menu.add(createCreateListingItem());
        menu.add(createViewListingItem());
        return menu;
    }

    private JMenuItem createCreateListingItem() {
        JMenuItem item = new JMenuItem("Create New Listing");
        class MenuItemListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                JFrame frame = new CreateListingFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setTitle("Create a Listing");
                frame.setVisible(true);
            }
        }
        ActionListener listener = new MenuItemListener();
        item.addActionListener(listener);
        return item;
    }

    private JMenuItem createViewListingItem() {
        return new JMenuItem("View your Listings");
    }

}


