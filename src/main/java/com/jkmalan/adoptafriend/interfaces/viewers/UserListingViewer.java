package com.jkmalan.adoptafriend.interfaces.viewers;

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.interfaces.UserListingPage;

import javax.swing.JFrame;

public class UserListingViewer {
    public static void main(String[] args) {
        AppEngine.enable();

        JFrame frame = new UserListingPage(1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Current Listings");
        frame.setVisible(true);
    }
}
