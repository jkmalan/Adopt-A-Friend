package com.jkmalan.adoptafriend.interfaces.viewers;


import com.jkmalan.adoptafriend.interfaces.CreateListingFrame;

import javax.swing.JFrame;

public class CreateListingViewer {
    public static void main(String[] args) {
        JFrame frame = new CreateListingFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Create a Listing");
        frame.setVisible(true);
    }
}
