package com.jkmalan.adoptafriend.interfaces.viewers;

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.interfaces.SearchPage;

import javax.swing.JFrame;

public class SearchFrameViewer {

    public static void main(String[] args) {
        AppEngine.enable();

        JFrame frame = new SearchPage(1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Search");
        frame.setVisible(true);
    }

}
