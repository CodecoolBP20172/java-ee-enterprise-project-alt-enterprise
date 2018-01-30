package com.codecool.restauratio.models.users;

public class Guest extends User{

    public Guest(String userName, String password) {
        super(userName, password, AccessRights.GUEST);
    }

}
