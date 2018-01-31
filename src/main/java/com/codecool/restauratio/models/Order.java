package com.codecool.restauratio.models;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Order extends Request{
    private String adress;
    private List<Food> foodList;
    
    public Order(String address, List<Food> foodList) {
        this.adress = address;
        this.foodList = foodList;
    }
}
