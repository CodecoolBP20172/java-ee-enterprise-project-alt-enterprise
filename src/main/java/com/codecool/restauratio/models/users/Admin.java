package com.codecool.restauratio.models.users;

public class Admin extends User{

    public Admin(String userName, String password) {
        super(userName, password, AccessRights.ADMIN);
    }


}
