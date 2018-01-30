package com.codecool.restauratio.models.users;

import org.mindrot.jbcrypt.BCrypt;

public abstract class User {

    private int userId;
    private String userName;
    private String password;
    private AccessRights accessRights;

    public User(String userName, String password, AccessRights accessRights) {
        this.userName = userName;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.accessRights = accessRights;
    }

    public int getUserId() {
        return userId;
    }

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
