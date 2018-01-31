package com.codecool.restauratio.models;

public class Reservation extends Request{
    private int id;
    private int numberOfPeople;

    private Reservation() {
        this.numberOfPeople = 0;
    }

    private Reservation(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
