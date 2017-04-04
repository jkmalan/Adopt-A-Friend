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
package com.jkmalan.adoptafriend.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomePage extends JFrame {

    private static final int FRAME_WIDTH = 480;
    private static final int FRAME_HEIGHT = 720;

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
            public void actionPerformed(ActionEvent e) {
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


