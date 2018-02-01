package com.codecool.restauratio.models.users;

import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Request;
import com.codecool.restauratio.models.Reservation;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

public class Guest extends User{

    // annotate to reservation and order tables with OneToMany
    @OneToMany(mappedBy = "user")
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
}