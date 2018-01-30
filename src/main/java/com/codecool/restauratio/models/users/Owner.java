package com.codecool.restauratio.models.users;

public class Owner extends User{

    public Owner(String userName, String password) {
        super(userName, password, AccessRights.OWNER);
    }


}
