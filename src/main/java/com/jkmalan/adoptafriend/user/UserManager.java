package com.jkmalan.adoptafriend.user;

import com.jkmalan.adoptafriend.AppEngine;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<Integer, User> userCache = new HashMap<>();

    public UserManager() {

    }

    public void shutdown() {
        userCache.clear();
    }

    public int validateUser(String username, String password) {
        int result = -1;
        User user = AppEngine.getDatabaseManager().selectUser(username);
        if (user != null) {
            result = user.getUserID();
        }
        return result;
    }

    public User getUser(int uid) {
        User user = null;
        if (userCache.containsKey(uid)) {
            user = userCache.get(uid);
        } else {
            user = AppEngine.getDatabaseManager().selectUser(uid);
            userCache.put(user.getUserID(), user);
        }
        return user;
    }

    public void createUser(String username, String password, String firstName, String lastName,
                           String email, String phone, String street, String city, String state, String zip,
                           String desc, String photo) {
        AppEngine.getDatabaseManager().insertUser(username, firstName, lastName, email, phone, street, city, state, zip, desc, photo);
        User user = AppEngine.getDatabaseManager().selectUser(username);
        userCache.put(user.getUserID(), user);
    }

    public void modifyUser(int uid, String username, String firstName, String lastName,
                           String email, String phone, String street, String city, String state, String zip,
                           String desc, String photo) {
        AppEngine.getDatabaseManager().updateUser(uid, username, firstName, lastName, email, phone, street, city, state, zip, desc, photo);
        User user = AppEngine.getDatabaseManager().selectUser(username);
        userCache.put(user.getUserID(), user);
    }

    public void deleteUser(int uid) {
        AppEngine.getDatabaseManager().deleteUser(uid);
        userCache.remove(uid);
    }

}
