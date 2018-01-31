package com.codecool.restauratio.models;

import javax.persistence.Entity;

@Entity
public class Reservation extends Request{

    private int numberOfPeople;

    public Reservation(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
