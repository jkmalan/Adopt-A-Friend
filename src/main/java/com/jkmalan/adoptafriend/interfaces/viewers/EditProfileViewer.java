package com.jkmalan.adoptafriend.interfaces.viewers;

import com.jkmalan.adoptafriend.AppEngine;
import com.jkmalan.adoptafriend.interfaces.EditProfilePage;
import com.jkmalan.adoptafriend.user.User;

import javax.swing.JFrame;

public class EditProfileViewer {

    public static void main(String[] args) {
        AppEngine.enable();

        JFrame frame = new EditProfilePage(1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Edit your account");
        frame.setVisible(true);
    }

}