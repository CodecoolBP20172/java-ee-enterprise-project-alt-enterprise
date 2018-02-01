package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation extends Request{

    @Column(name = "number_of_people")
    private int numberOfPeople;

    @ManyToOne
    @JoinColumn(name = "reservation_restaurant_id")
    private Restaurant reservationRestaurant;

    @ManyToOne
    private User guest;


    public Reservation(Date date, int numberOfPeople, Restaurant r, User g) {
        super(date);
        this.numberOfPeople = numberOfPeople;
        this.reservationRestaurant = r;
        this.guest = g;
    }


    public Reservation() {
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
    public Restaurant getReservationRestaurant() {
        return reservationRestaurant;
    }

    public void setReservationRestaurant(Restaurant reservationRestaurant) {
        this.reservationRestaurant = reservationRestaurant;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }
}
