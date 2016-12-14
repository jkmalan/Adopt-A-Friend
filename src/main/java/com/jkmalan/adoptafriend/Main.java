package com.jkmalan.adoptafriend;

import com.jkmalan.adoptafriend.interfaces.LoginFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        AppEngine.enable();

        JFrame frame = new LoginFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
