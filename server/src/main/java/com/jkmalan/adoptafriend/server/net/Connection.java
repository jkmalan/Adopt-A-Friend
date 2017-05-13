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

import com.jkmalan.adoptafriend.common.user.User;
import com.jkmalan.adoptafriend.server.ServerEngine;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Represents a single connection thread from a client
 *
 * This class exists primarily to handle just the connection
 * but at the moment it is also being used as a temporary home
 * for command and argument validation and processing. In the
 * future, a Command API will be designed to do advanced
 * argument processing more neatly and efficiently.
 */
public class Connection implements Runnable {

    private final Socket client;

    private InputStream ois;
    private OutputStream oos;

    public Connection(Socket client) {
        this.client = client;
        try {
            ois = client.getInputStream();
            oos = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean clientAlive = true;
        while (clientAlive) {
            Scanner input = new Scanner(ois);
            PrintWriter output = new PrintWriter(oos);

            String command = "";
            if (input.hasNextLine()) {
                command = input.nextLine();
            }
            String[] args = command.split(" ");

            // Eventually abstract this into its own method/class
            // Command API?
            String response = "";
            if (args.length == 3 && args[0].equals("USER-VALIDATE")) {
                response = handleUserValidate(args);
            } else if (args.length == 2 && args[0].equals("USER-GET-UID")) {
                response = handleUserGetUID(args);
            } else if (args.length == 2 && args[0].equals("USER-GET-NAME")) {
                response = handleUserGetName(args);
            } else if (args.length == 2 && args[0].equals("USER-CREATE")) {
                response = handleUserCreate(args);
            } else if (args.length == 2 && args[0].equals("USER-MODIFY")) {
                response = handleUserModify(args);
            } else if (args.length == 2 && args[0].equals("USER-DELETE")) {
                response = handleUserDelete(args);
            } else {
                clientAlive = false;
            }

            output.println(response);
            output.flush();
        }

        try {
            client.close();
        } catch (IOException e) {
            // TODO Failure to close client
        }
    }

    /**
     * Handles a user validation command
     * Format: USER-VALIDATE username password
     *
     * @param args The arguments array
     * @return The unique internal id
     */
    private String handleUserValidate(String[] args) {
        int uid = ServerEngine.getUserManager().validateCredentials(args[1], args[2].toCharArray());
        return "DATA " + uid;
    }

    /**
     * Handles a user get by uid command
     * Format: USER-GET-UID uid
     *
     * @param args The arguments array
     * @return The User object as fields
     */
    private String handleUserGetUID(String[] args) {
        User user = ServerEngine.getUserManager().getUser(Integer.parseInt(args[1]));
        if (user == null) {
            return "ERROR User with uid " + args[1] + " does not exist";
        }
        return "DATA " + user.getUID() + " " + user.getUsername() + " " + user.getFirstName() + " "
                + user.getLastName() + " " + user.getEmail() + " " + user.getPhone() + " "
                + user.getStreet() + " " + user.getCity() + " " + user.getState() + " "
                + user.getZip();
    }

    /**
     * Handles a user get by username command
     * Format: USER-GET-UID username
     *
     * @param args The arguments array
     * @return The User object as fields
     */
    private String handleUserGetName(String[] args) {
        User user = ServerEngine.getUserManager().getUser(args[1]);
        if (user == null) {
            return "ERROR User with username " + args[1] + " does not exist";
        }
        return "DATA " + user.getUID() + " " + user.getUsername() + " " + user.getFirstName() + " "
                + user.getLastName() + " " + user.getEmail() + " " + user.getPhone() + " "
                + user.getStreet() + " " + user.getCity() + " " + user.getState() + " "
                + user.getZip();
    }

    /**
     * Handles a user create command
     * Format: USER-CREATE username password firstName lastName email phone street city state zip
     *
     * @param args The arguments array
     * @return The success message
     */
    private String handleUserCreate(String[] args) {
        String username = args[1];
        char[] password = args[2].toCharArray();
        String firstName = args[3];
        String lastName = args[4];
        String email = args[5];
        String phone = args[6];
        String street = args[7];
        String city = args[8];
        String state = args[9];
        String zip = args[10];
        ServerEngine.getUserManager().createUser(username, password, firstName, lastName, email, phone, street, city, state, zip);
        return "SUCCESS";
    }

    /**
     * Handles a user modify command
     * Format: USER-MODIFY uid username password firstName lastName email phone street city state zip
     *
     * @param args The arguments array
     * @return The success message
     */
    private String handleUserModify(String[] args) {
        int uid = Integer.parseInt(args[1]);
        String username = args[2];
        char[] password = args[3].toCharArray();
        String firstName = args[4];
        String lastName = args[5];
        String email = args[6];
        String phone = args[7];
        String street = args[8];
        String city = args[9];
        String state = args[10];
        String zip = args[11];
        ServerEngine.getUserManager().modifyUser(uid, username, password, firstName, lastName, email, phone, street, city, state, zip);
        return "SUCCESS";
    }

    /**
     * Handles a user delete command
     * Format: USER-DELETE uid
     *
     * @param args The arguments array
     * @return The success message
     */
    private String handleUserDelete(String[] args) {
        ServerEngine.getUserManager().deleteUser(Integer.parseInt(args[1]));
        return "SUCCESS";
    }

}
