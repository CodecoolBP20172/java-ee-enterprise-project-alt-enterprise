package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.Guest;
import com.codecool.restauratio.models.users.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public abstract class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

}
