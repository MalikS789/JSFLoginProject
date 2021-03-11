package com.sparta.malik.JSFLoginProject.services;

import com.sparta.malik.JSFLoginProject.datastore.Database;
import com.sparta.malik.JSFLoginProject.entities.Users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserService {

    @Inject
    Users user;

    @Inject
    private Database database;

    String errorMsg;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String welcome() {
        Users tempUser = database.findUser(user.getUsername());
        if (tempUser != null) {
            if (user.getPassword().equals(tempUser.getPassword())) {
                return "welcome";
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
