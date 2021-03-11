package com.sparta.malik.JSFLoginProject.authentication;

import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;

public class InMemoryIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult  validate(Credential credential) {
        UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
        if (usernamePasswordCredential.getCaller().equals("Ringo")
            && usernamePasswordCredential.getPasswordAsString().equals("Star")) {
            HashSet<String> roles = new HashSet<>();
            roles.add("ADMIN");
            return new CredentialValidationResult("Ringo Star", roles);
        } else if (usernamePasswordCredential.getCaller().equals("John")
                && usernamePasswordCredential.getPasswordAsString().equals("Lennon")) {
            HashSet<String> roles = new HashSet<>();
            roles.add("ADMIN");
            return new CredentialValidationResult("John Lennon", roles);
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }

}
