package com.codecool.restauratio.models.users;

public class Admin extends User{

    // annotate with id+generatedvalue
    private long adminId;

    public Admin(String userName, String password) {
        super(userName, password, AccessRights.ADMIN);
    }

    @Override
    public long getUserId() {
        return adminId;
    }
}
