package com.codecool.restauratio.models.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
