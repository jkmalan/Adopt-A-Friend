/*
* This file is part of Adopt-A-Friend, licensed under the MIT License (MIT).
*
* Copyright (c) Adopt-A-Friend
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/
package com.jkmalan.adoptafriend.client;

import com.jkmalan.adoptafriend.client.gui.LoginFrame;
import com.jkmalan.adoptafriend.client.net.Connection;

import javax.swing.*;

/**
 * Provides access to the interface and server
 */
public class ClientEngine {

    public static final String IP_ADDR = "127.0.0.1";
    public static final int PORT = 32000;

    public static final int FRAME_WIDTH = 360;
    public static final int FRAME_HEIGHT = 640;

    private static ClientEngine ENGINE = null;

    private final Connection connection;

    private ClientEngine() {
        connection = new Connection(IP_ADDR, PORT);

        JFrame frame = new LoginFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Initializes the ClientEngine frontend
     */
    public static void enable() {
        if (ENGINE == null) {
            ENGINE = new ClientEngine();
        }
    }

    /**
     * Gets the Connection to the server
     *
     * @return The ClientEngine Connection
     */
    public static Connection getConnection() {
        return ENGINE.connection;
    }

    /**
     * Switches between two different frames
     *
     * @param background The frame to dispose
     * @param foreground The frame to create
     */
    public static void switchFrame(JFrame background, JFrame foreground) {
        foreground.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        foreground.setVisible(true);

        background.dispose();
    }

}
