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

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.listing.Listing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SearchListingPage extends JFrame {

    private static final int FRAME_WIDTH = 480;
    private static final int FRAME_HEIGHT = 720;

    private JPanel searchListingPanel;

    private List<Listing> listings;

    public SearchListingPage(List<Listing> listings) {
        this.listings = listings;

        buildUserListingPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
        listingPanel.add(buildEditButton(listing));
        listingPanel.add(buildDeleteButton(listing));
        return listingPanel;
    }

    private JButton buildEditButton(Listing listing) {
        JButton editButton = new JButton("Edit");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new EditListingPage(listing.getListingID());
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        };
        editButton.addActionListener(listener);
        return editButton;
    }

    public JButton buildDeleteButton(Listing listing) {
        JButton deleteButton = new JButton("Delete");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppEngine.getListingManager().deleteListing(listing.getListingID());
                JOptionPane.showMessageDialog(searchListingPanel, "Listing successfully deleted!");
                searchListingPanel.revalidate();
                searchListingPanel.repaint();
            }
        };
        deleteButton.addActionListener(listener);
        return deleteButton;
    }

    private void buildUserListingPanel() {
        searchListingPanel = new JPanel();

        for (Listing l : listings) {
            searchListingPanel.add(buildListingPanel(l));
        }

        // TODO Scrollbar...
        add(searchListingPanel);
    }

}
	
	

