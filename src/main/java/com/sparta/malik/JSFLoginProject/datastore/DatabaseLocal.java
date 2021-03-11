package com.sparta.malik.JSFLoginProject.datastore;

import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

public class DatabaseLocal {

    HashMap<String, UserEntity> users = new HashMap<>();
    private String errorMsg;
    private static final String[] validUserTypes = {"admin","user"};

    {
        addUser("admin", "password", "admin");
        addUser("Bob", "Smith", "user");
        addUser("Steve", "Jones", "user");
    }

    public UserEntity findUser(String username) {
        return users.get(username);
    }

    public List<UserEntity> getAllUsers() {
        Collection<UserEntity> values = users.values();
        ArrayList<UserEntity> listOfValues = new ArrayList<>(values);
        return listOfValues;
    }

    public void addUser(String username, String password, String userType) {
        //check if username already exists!
        if (findUser(username) == null) {
            //get ids of all users, to find the next available id
            // use that id to add into the database
            // MD5 hash password
            if (MD5(password) == null) {
                errorMsg = "Unable to set password, sorry.";
            } else {
                // check validation for userType
                if (Arrays.binarySearch(validUserTypes, userType) == -1) {
                    errorMsg = "invalid userType specified!";
                } else {
                    UserEntity user = new UserEntity();
                    user.setId(getAllUsers().size());
                    user.setUsername(username);
                    user.setPassword(MD5(password));
                    user.setUserType(userType);
                    users.put(username, user);
                }
            }
        } else {
            errorMsg = "User already exists";
        }
    }

    public static String MD5(String input) {
        try {
            byte[] bytesOfMessage = input.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            return new String(thedigest);
        } catch (Exception e) {
            return null;
        }
    }

}
