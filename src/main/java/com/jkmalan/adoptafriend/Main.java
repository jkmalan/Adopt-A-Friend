package com.jkmalan.adoptafriend;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        AppEngine.init();
        AppInterface.init();

        AppInterface.getFoundationFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AppInterface.getFoundationFrame().setVisible(true);
    }

}
