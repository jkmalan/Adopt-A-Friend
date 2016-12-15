package com.jkmalan.adoptafriend.interfaces.viewers;


import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.interfaces.CreateListingPage;
import com.jkmalan.adoptafriend.user.User;

import javax.swing.JFrame;

public class CreateListingViewer {

    public static void main(String[] args) {
        AppEngine.enable();

        JFrame frame = new CreateListingPage(1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Create a Listing");
        frame.setVisible(true);
    }

}
