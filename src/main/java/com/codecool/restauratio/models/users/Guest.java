package com.codecool.restauratio.models.users;

import java.util.List;
import java.util.ArrayList;

public class Guest extends User{

    // annotate to reservation and order tables with OneToMany
    private List<Reservation> reservations;
    private List<Order> orders;

    public Guest(String userName, String password) {
        super(userName, password, AccessRights.GUEST);
        reservations = new ArrayList<Reservation>();
        orders = new ArrayList<Order>();
    }

}
