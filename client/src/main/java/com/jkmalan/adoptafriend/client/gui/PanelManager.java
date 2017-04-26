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
package com.jkmalan.adoptafriend.client.gui;

import com.jkmalan.adoptafriend.common.user.User;

import javax.swing.*;
import java.awt.*;

/**
 * @author jkmalan (John Malandrakis)
 */
public class PanelManager extends JFrame {

    private static PanelManager PANELMANAGER = null;

    public static final int FRAME_WIDTH = 540;
    public static final int FRAME_HEIGHT = 960;

    private JMenuBar menuBar;

    private PanelManager() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);


    }

    public static PanelManager getPanelManager() {
        if (PANELMANAGER == null) {
            PANELMANAGER = new PanelManager();
        }
        return PANELMANAGER;
    }

    private User loggedin = null;

    private void buildLoginPanel() {

    }

    private void buildPanelLayout() {
        getContentPane().setLayout(new CardLayout());
        getContentPane().add(new HomePanel(loggedin), "HomePanel");
    }

}
