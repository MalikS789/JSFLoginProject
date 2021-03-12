package com.sparta.malik.JSFLoginProject.authentication;

import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

@Named
@RequestScoped
public class LogoutBean {
    @Inject
    UserEntity userEntity;

    @Inject
    FacesContext facesContext;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String logout() throws ServletException {
        facesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}