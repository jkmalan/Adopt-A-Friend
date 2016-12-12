package com.jkmalan.adoptafriend.interfaces;

import javax.swing.*;

public class FoundationFrame extends JFrame {

    private final int FRAME_WIDTH = 640;
    private final int FRAME_HEIGHT = 480;

    private JPanel loginPanel;
    private JPanel recoveryPanel;
    private JPanel createPanel;

    public FoundationFrame() {
        loginPanel = new LoginPanel();
        recoveryPanel = new RecoveryPanel();
        createPanel = new CreatePanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        add(loginPanel);
    }

}
