package com.sparta.malik.JSFLoginProject.authentication;

import com.sparta.malik.JSFLoginProject.datastore.UserRepository;
import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import static com.sparta.malik.JSFLoginProject.datastore.UserRepository.MD5;

@Named
@RequestScoped
public class RegistrationBean {

    @Inject
    UserEntity userEntity;

    @Inject
    UserRepository userRepository;

    @Inject
    FacesContext facesContext;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String register() {
        int newID = userRepository.getHighestIdUser().getId() + 1;
        UserEntity newUserEntity = new UserEntity();
        if (!validateNewUser(userEntity)) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username and/or password cannot be empty.", null));
            return "registration";
        } else {
            try {
                userRepository.getUserByUsername(userEntity.getUsername());
            } catch (NoResultException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                newUserEntity.setId(newID);
                newUserEntity.setUsername(userEntity.getUsername());
                newUserEntity.setPassword(MD5(userEntity.getPassword()));
                newUserEntity.setUserType(userEntity.getUserType());
                userRepository.addUser(newUserEntity);
                return "registrationSuccess";
            }
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User was not added because a user with the same username already exists", null));
            return "registration";
        }
    }

    private boolean validateNewUser(UserEntity user) {
        return isFieldValid(user.getUsername()) && isFieldValid(user.getPassword());
    }

    private boolean isFieldValid(String fieldInput) {
        return fieldInput.length() > 1;
    }
}

