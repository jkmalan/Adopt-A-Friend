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
package com.jkmalan.adoptafriend.server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listens for connections from clients
 *
 * DEFAULT HOST: 127.0.0.1 (localhost)
 * DEFAULT PORT: 32000
 *
 * This is a slightly different type of manager class than
 * the others due to the ability to start and stop the server
 * without crashing the entire server program. It must be done
 * programmatically and as such it may be added as a server
 * management command from a terminal at some point in the future.
 * As of right now, there is no way to stop the server without
 * crashing the program since it is running in the main thread.
 */
public class ConnectionManager {

    private final int PORT = 32000;

    private ServerSocket server;

    private boolean listening = true;

    public ConnectionManager() {
        try {
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            // TODO Failure to start server
        }
    }

    /**
     * Start listening for connections
     */
    public void startListening() {
        if (!listening) {
            listening = true;
        }

        while (listening) {
            try {
                Socket client = server.accept();
                Thread connection = new Thread(new Connection(client));
                connection.start();
            } catch (IOException e) {
                // TODO Failure to accept client
            }
        }
    }

    /**
     * Stop listening for connections
     *
     * Does not force the server to stop
     * It will no longer accept new clients
     */
    public void stopListening() {
        if (listening) {
            listening = false;
        }
    }

    /**
     * Checks if the server is accepting connections
     *
     * @return If server is accepting, returns true
     */
    public boolean isListening() {
        return listening;
    }

}
