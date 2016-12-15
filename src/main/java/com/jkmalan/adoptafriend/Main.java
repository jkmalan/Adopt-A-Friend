package com.jkmalan.adoptafriend;

import com.jkmalan.adoptafriend.interfaces.LoginPage;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        AppEngine.enable();

        JFrame frame = new LoginPage();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

}
