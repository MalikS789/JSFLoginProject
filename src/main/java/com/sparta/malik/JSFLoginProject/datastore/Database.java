package com.sparta.malik.JSFLoginProject.datastore;

import com.sparta.malik.JSFLoginProject.entities.Users;

import javax.inject.Named;
import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

@Named
public class Database {

    private static String errorMsg;
    private static final String[] validUserTypes = {"admin","user"};

    public static void addUser(String username, String password, String userType) {
        //check if username already exists!
        if (findUser(username) == null) {
            //get ids of all users, to find the next available id
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Integer> query2 = entityManager.createQuery("SELECT MAX(id) FROM Users", Integer.class);
            List<Integer> highestID = query2.getResultList();
            // use that id to add into the database
            // MD5 hash password
            if (MD5(password) == null) {
                errorMsg = "Unable to set password, sorry.";
            } else {
                // check validation for userType
                if (Arrays.binarySearch(validUserTypes, userType) == -1) {
                    errorMsg = "invalid userType specified!";
                } else {
                    EntityTransaction transaction = entityManager.getTransaction();
                    transaction.begin();
                    Users user = new Users();
                    user.setId(highestID.get(0) + 1);
                    user.setUsername(username);
                    user.setPassword(MD5(password));
                    entityManager.persist(user);
                    transaction.commit();
                }
            }
            entityManager.close();
            entityManagerFactory.close();
        } else {
            errorMsg = "User already exists";
        }
    }

    public static List<Users> getAllUsers() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        TypedQuery<Users> query = entityManager.createQuery("SELECT id,username,password,userType FROM Users", Users.class);
        return query.getResultList();
    }

    public static Users findUser(String username) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users AS u WHERE u.username = " + username + "", Users.class);
        List<Users> results = query.getResultList();
        if (results.isEmpty()) {
            entityManager.close();
            entityManagerFactory.close();
            return null;
        } else {
            entityManager.close();
            entityManagerFactory.close();
            return results.get(0);
        }
    }

    private static String MD5(String input) {
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
