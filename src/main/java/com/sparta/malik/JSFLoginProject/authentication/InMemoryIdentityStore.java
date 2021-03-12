package com.sparta.malik.JSFLoginProject.authentication;

import com.sparta.malik.JSFLoginProject.datastore.UserRepository;
import com.sparta.malik.JSFLoginProject.entities.UserEntity;

import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;

public class InMemoryIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;

        for (UserEntity user : new UserRepository().getAllUsers()) {
            if (usernamePasswordCredential.getCaller().equals(user.getUsername())
                    && usernamePasswordCredential.getPasswordAsString().equals(user.getPassword())) {
                HashSet<String> roles = new HashSet<>();
                if (user.getUserType().equals("admin")) {
                    roles.add("ADMIN");
                } else {
                    roles.add("USER");
                }
                return new CredentialValidationResult(user.getUsername(), roles);
            }
        }

        return CredentialValidationResult.NOT_VALIDATED_RESULT;

    }
}