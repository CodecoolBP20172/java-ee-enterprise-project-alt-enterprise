package com.codecool.restauratio.models;

import java.util.List;

public class Order extends Request{
    private int id;
    private String adress;
    private List<Food> foodList;

    private Order() {
        this.adress = "";
        this.foodList = null;
    }
    
    private Order(String address, List<Food> foodList) {
        this.adress = address;
        this.foodList = foodList;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
