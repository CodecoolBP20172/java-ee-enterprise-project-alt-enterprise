package com.codecool.restauratio.models.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    // annotate with id+generatedvalue
    private long adminId;

    public Admin(String userName, String password) {
        super(userName, password, AccessRights.ADMIN);
    }

    public Admin() {
    }

    @Override
    public long getUserId() {
        return adminId;
    }
}
