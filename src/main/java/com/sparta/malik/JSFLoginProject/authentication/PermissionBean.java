package com.sparta.malik.JSFLoginProject.authentication;

import com.sparta.malik.JSFLoginProject.datastore.UserRepository;
import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
@RequestScoped
public class PermissionBean {
    @Inject
    UserEntity userEntity;

    @Inject
    UserRepository userRepository;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Collection<UserEntity> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public String changePermission() {
        UserEntity user = userRepository.getUserByUsername(userEntity.getUsername());
        userRepository.updatePermission(userEntity.getUserType(), user);
        return "permissionSuccess";
    }
}
