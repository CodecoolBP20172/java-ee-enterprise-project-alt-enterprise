package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservation extends Request{

    private int numberOfPeople;

    @ManyToOne
    private Restaurant reservationRestaurant;

    @ManyToOne
    private User guest;

    public Reservation(int numberOfPeople, Restaurant restaurant, User user) {
        this.numberOfPeople = numberOfPeople;
        this.reservationRestaurant = restaurant;
        this.guest = user;
    }

    public Reservation() {
    }




}
