package com.sparta.malik.JSFLoginProject.services;

import com.sparta.malik.JSFLoginProject.datastore.DatabaseJPA;
import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import static com.sparta.malik.JSFLoginProject.datastore.DatabaseJPA.MD5;

@Named
@RequestScoped
public class UserService {

    @Inject
    UserEntity user;

    @Inject
    private DatabaseJPA database;

    String errorMsg;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String welcome() {
        UserEntity userFromDatabase = database.findUser(user.getUsername());
        if (userFromDatabase != null) {
            if (MD5(user.getPassword()).equals(userFromDatabase.getPassword())) {
                switch (userFromDatabase.getUserType()) {
                    case "admin":
                        return "admin";
                    case "user":
                        return "welcome";
                    default:
                        return "error";
                }
            } else {
                errorMsg = "The password you entered isn't correct! (" + MD5(user.getPassword()) + " , " + userFromDatabase.getPassword() + " ) ";
                return "error";
            }
        } else {
            errorMsg = "The username you entered (" + user.getUsername() + ") doesn't exist!";
            return "error";
        }
    }

}
