package com.codecool.restauratio.models.users;

import com.codecool.restauratio.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User{

    // annotate with id+generatedvalue
    private long ownerId;

    // annotate with OneToMany to restaurant table
    private List<Restaurant> restaurants;

    public Owner(String userName, String password) {
        super(userName, password, AccessRights.OWNER);
        restaurants = new ArrayList<Restaurant>();
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public long getUserId() {
        return ownerId;
    }
}
