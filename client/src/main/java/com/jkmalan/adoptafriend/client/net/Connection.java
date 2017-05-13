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
package com.jkmalan.adoptafriend.client.net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Represents a connection to the server
 */
public class Connection {

    private final String ip;
    private final int port;

    public Connection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String exchangeData(String send) {
        String receive = "";
        try {
            Socket server = new Socket(ip, port);
            InputStream is = server.getInputStream();
            OutputStream os = server.getOutputStream();

            Scanner input = new Scanner(is);
            PrintWriter output = new PrintWriter(os);

            System.out.println(send);

            output.println(send);
            output.flush();

            receive = input.nextLine();
            System.out.println(receive);

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receive;
    }

}
