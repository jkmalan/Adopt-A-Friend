package com.jkmalan.adoptafriend;

import com.jkmalan.adoptafriend.interfaces.FoundationFrame;

public class AppInterface {

    private static AppInterface INTERFACE = null;

    private final FoundationFrame foundationFrame;

    private AppInterface() {
        foundationFrame = new FoundationFrame();
    }

    protected static void init() {
        INTERFACE = new AppInterface();
    }

    public static FoundationFrame getFoundationFrame() {
        return INTERFACE.foundationFrame;
    }

}
