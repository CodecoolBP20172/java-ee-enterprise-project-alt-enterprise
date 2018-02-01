package com.codecool.restauratio.models.users;

import com.codecool.restauratio.models.Restaurant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
@Entity
public class Owner extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerId;

    @CollectionTable(name="phone")
    @ElementCollection
    private List<Restaurant> restaurants;

    public Owner(String userName, String password) {
        super(userName, password, AccessRights.OWNER);
        restaurants = new ArrayList<Restaurant>();
    }

    public Owner() {
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public long getUserId() {
        return ownerId;
    }
}

*/