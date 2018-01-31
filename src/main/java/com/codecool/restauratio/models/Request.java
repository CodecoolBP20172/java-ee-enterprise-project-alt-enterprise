package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;

import java.util.Date;

public abstract class Request {
    private Date creationDate;
    private User user;
    private Restaurant restaurant;


    public abstract int getId();
}
