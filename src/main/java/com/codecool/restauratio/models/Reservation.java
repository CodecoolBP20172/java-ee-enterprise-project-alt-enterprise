package com.codecool.restauratio.models;

import javax.persistence.Entity;

@Entity
public class Reservation extends Request{

    private int numberOfPeople;

    private Reservation() {
        this.numberOfPeople = 0;
    }

    private Reservation(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
