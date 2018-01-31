package com.codecool.restauratio.models.users;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
