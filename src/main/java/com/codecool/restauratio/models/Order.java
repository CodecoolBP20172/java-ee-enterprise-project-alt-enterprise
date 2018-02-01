package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@NamedQueries({
        @NamedQuery(
                name = "getAllOrder",
                query = "SELECT o FROM Order o"),
        @NamedQuery(
                name = "getOrderById",
                query = "select o FROM Order o where o.id = :id")
})
public class Order extends Request{
    private String address;

    @ManyToMany
    @JoinTable(
            name = "Order_Food",
            joinColumns = { @JoinColumn(name = "order_id")},
            inverseJoinColumns = { @JoinColumn(name = "food_id")}
    )

    private List<Food> foodList;
    @ManyToOne
    @JoinColumn(name = "target_order_restaurant_id")
    private Restaurant orderRestaurant;

    @ManyToOne
    private User user;

    public Order() {
    }

    public Order(Date date, String address, List<Food> foodList, User user, Restaurant restaurant) {
        super(date);
        this.address = address;
        this.foodList = foodList;
        this.orderRestaurant = restaurant;
        this.user = user;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Restaurant getOrderRestaurant() {
        return orderRestaurant;
    }

    public void setOrderRestaurant(Restaurant orderRestaurant) {
        this.orderRestaurant = orderRestaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order = " +
                "address: '" + address + '\'' +
                ", foodList: " + foodList +
                ", orderRestaurant: " + orderRestaurant.getName() +
                ", user: " + user.getUserName();
    }
}
