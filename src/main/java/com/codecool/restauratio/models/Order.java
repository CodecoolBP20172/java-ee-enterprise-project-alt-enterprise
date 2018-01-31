package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order extends Request{
    private String adress;
    // private List<Food> foodList;

    @ManyToOne
    private Restaurant orderRestaurant;

    @ManyToOne
    private User user;

    public Order(String address, List<Food> foodList, User user, Restaurant restaurant) {
        this.adress = address;
        // this.foodList = foodList;
        this.orderRestaurant = restaurant;
        this.user = user;

    }

    public Order() {
    }
}
