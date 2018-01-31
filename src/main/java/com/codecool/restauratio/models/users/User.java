package com.codecool.restauratio.models.users;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@MappedSuperclass
public abstract class User {

    protected String userName;
    protected String password;

    @Enumerated(EnumType.STRING)
    private AccessRights accessRights;

    public User(String userName, String password, AccessRights accessRights) {
        this.userName = userName;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.accessRights = accessRights;
    }

    protected User() {
    }

    public abstract long getUserId();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public AccessRights getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(AccessRights accessRights) {
        this.accessRights = accessRights;
    }

    public boolean checkPassword(String candidatePassword){
        return BCrypt.checkpw(candidatePassword, password);
    }
}
