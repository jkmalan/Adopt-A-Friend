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

import com.jkmalan.adoptafriend.client.ClientEngine;
import com.jkmalan.adoptafriend.client.gui.listing.CreateListingFrame;
import com.jkmalan.adoptafriend.client.gui.listing.ListingsFrame;
import com.jkmalan.adoptafriend.client.gui.profile.EditProfileFrame;
import com.jkmalan.adoptafriend.common.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A basic home page
 *
 * User may access other pages from the menu bar
 */
public class HomeFrame extends JFrame {

    private JMenuBar menuBar;

    private JPanel homePanel;

    private User user;

    public HomeFrame(int uid) {
        /*
         * COMMAND: USER-GET-UID uid
         * Send uid
         * Receive 10 user fields
         */
        String data = ClientEngine.getConnection().exchangeData("USER-GET-UID " + uid);
        String[] args = data.split(" ");
        if (args[0].equals("DATA")) {
            user = new User(Integer.parseInt(args[1]), args[2]);
            user.setFirstName(args[3]);
            user.setLastName(args[4]);
            user.setEmail(args[5]);
            user.setPhone(args[6]);
            user.setStreet(args[7]);
            user.setCity(args[8]);
            user.setState(args[9]);
            user.setZip(args[10]);
        }

        setSize(ClientEngine.FRAME_WIDTH, ClientEngine.FRAME_HEIGHT);
        setTitle("Home");

        buildMenuBar();

        buildHomePanel();
    }

    /*
     * Initializes the profile menu items
     */
    private JMenu buildProfileMenu() {
        JMenu profileMenu = new JMenu("Profile");
        JMenuItem profileEditItem = new JMenuItem("Edit");
        profileEditItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new EditProfileFrame(user);
                ClientEngine.switchFrame(HomeFrame.this, frame);
            }
        });
        JMenuItem profileLogoutItem = new JMenuItem("Logout");
        profileLogoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new LoginFrame();
                ClientEngine.switchFrame(HomeFrame.this, frame);
            }
        });

        profileMenu.add(profileEditItem);
        profileMenu.add(profileLogoutItem);
        return profileMenu;
    }

    /*
     * Initializes the listing menu items
     */
    private JMenu buildListingMenu() {
        JMenu listingMenu = new JMenu("Listing");
        JMenuItem listingViewItem = new JMenuItem("View");
        listingViewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new ListingsFrame(user);
                ClientEngine.switchFrame(HomeFrame.this, frame);
            }
        });
        JMenuItem listingCreateItem = new JMenuItem("Create");
        listingCreateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateListingFrame(user);
                ClientEngine.switchFrame(HomeFrame.this, frame);
            }
        });

        listingMenu.add(listingViewItem);
        listingMenu.add(listingCreateItem);
        return listingMenu;
    }

    /*
     * Constructs the menu bar for the frame
     */
    private void buildMenuBar() {
        menuBar = new JMenuBar();
        menuBar.add(buildProfileMenu());
        menuBar.add(buildListingMenu());
        setJMenuBar(menuBar);
    }

    /*
     * Constructs the home panel for the frame
     */
    private void buildHomePanel() {
        homePanel = new JPanel();

        add(homePanel);
    }

}
