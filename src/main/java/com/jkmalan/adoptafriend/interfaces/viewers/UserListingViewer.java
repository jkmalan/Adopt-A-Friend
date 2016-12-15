package com.jkmalan.adoptafriend.interfaces.viewers;

import com.jkmalan.adoptafriend.interfaces.UserListingPage;

import javax.swing.JFrame;

public class UserListingViewer {
    public static void main(String[] args) {
        JFrame frame = new UserListingPage();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Current Listings");
        frame.setVisible(true);
    }
}
