package com.codecool.restauratio.models;

public class Reservation extends Request{
    private int id;
    private int numberOfPeople;


    @Override
    public int getId() {
        return this.id;
    }
}
