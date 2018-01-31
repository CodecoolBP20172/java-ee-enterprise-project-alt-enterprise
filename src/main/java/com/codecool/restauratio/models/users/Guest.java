package com.codecool.restauratio.models.users;

import java.util.List;
import java.util.ArrayList;

public class Guest extends User{

    // annotate with id+generatedvalue
    private long guestId;

    // annotate to reservation and order tables with OneToMany
    private List<Reservation> reservations;
    private List<Order> orders;

    public Guest(String userName, String password) {
        super(userName, password, AccessRights.GUEST);
        reservations = new ArrayList<Reservation>();
        orders = new ArrayList<Order>();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public long getUserId() {
        return guestId;
    }
}
