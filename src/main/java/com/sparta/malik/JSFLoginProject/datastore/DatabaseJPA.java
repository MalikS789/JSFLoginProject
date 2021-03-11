package com.sparta.malik.JSFLoginProject.datastore;

import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.inject.Named;
import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

@Named
public class DatabaseJPA {

    private static String errorMsg;
    private static final String[] validUserTypes = {"admin","user"};

    public void addUser(String username, String password, String userType) {
        //check if username already exists!
        if (findUser(username) == null) {
            //get ids of all users, to find the next available id
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Query nativeQuery = entityManager.createNativeQuery("SELECT MAX(id) FROM Users", UserEntity.class);
            int highestID = (int) nativeQuery.getSingleResult();
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
                    UserEntity user = new UserEntity();
                    user.setId(highestID + 1);
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

    public List<UserEntity> getAllUsers() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<UserEntity> users = entityManager.createQuery("select u from UserEntity u", UserEntity.class).getResultList();
        return users;
    }

    public UserEntity findUser(String username) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM Users WHERE username=:userId", UserEntity.class);
        nativeQuery.setParameter("userId", username);

        UserEntity tempUser;
        try {
            tempUser = (UserEntity) nativeQuery.getSingleResult();
        } catch (NoResultException e) {
            tempUser = null;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return tempUser;

//         if ((Users) nativeQuery.getSingleResult() == null) {
////        TypedQuery<Users> query = entityManager.createQuery("SELECT id,username,password,userType FROM Users WHERE username = '" + username + "'", Users.class);
////        List<Users> results = query.getResultList();
////        if (results.isEmpty()) {
//            entityManager.close();
//            entityManagerFactory.close();
//            return null;
//        } else {
//            entityManager.close();
//            entityManagerFactory.close();
////            return results.get(0);
//            return (Users) nativeQuery.getSingleResult();
//        }
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
