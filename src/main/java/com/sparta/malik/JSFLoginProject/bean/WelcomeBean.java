package com.sparta.malik.JSFLoginProject.bean;

import com.sparta.malik.JSFLoginProject.datastore.UserRepository;
import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class WelcomeBean {

    @Inject
    UserEntity userEntity;

    @Inject
    UserRepository userRepository;

    @Inject
    ExternalContext externalContext;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void onload() {
        // Do your stuff here.
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        String userType = userRepository.getUserByUsername(username).getUserType();
        if (userType.equals("admin")) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/view/admin.xhtml");
            } catch (IOException ignored) {
            }
        }
    }

}
