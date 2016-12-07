package com.jkmalan.adoptafriend.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private List<User> userCache;

    public UserManager() {
        userCache = new ArrayList<>();
    }

    public void createUser(String name, String email, String password) {

    }

    public void modifyUser(int uid, String name, String email, String phone, String street, String city, String state, String zip, String desc, File photo) {

    }

    public void deleteUser(int uid) {

    }

}
