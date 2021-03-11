package com.sparta.malik.JSFLoginProject.services;

import com.sparta.malik.JSFLoginProject.datastore.DatabaseJPA;
import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import static com.sparta.malik.JSFLoginProject.datastore.DatabaseLocal.MD5;

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
        UserEntity tempUser = database.findUser(user.getUsername());
        user.setPassword(MD5(tempUser.getPassword()));
        if (tempUser != null) {
            if (user.getPassword().equals(tempUser.getPassword())) {
                switch (tempUser.getUserType()) {
                    case "admin":
                        return "admin";
                    case "user":
                        return "welcome";
                    default:
                        return "error";
                }
            } else {
                errorMsg = "The password you entered isn't correct!";
                return "error";
            }
        } else {
            errorMsg = "The username you entered (" + user.getUsername() + ") doesn't exist!";
            return "error";
        }
    }

}
