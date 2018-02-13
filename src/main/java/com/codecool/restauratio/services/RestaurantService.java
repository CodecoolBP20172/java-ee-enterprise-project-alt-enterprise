package com.codecool.restauratio.services;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.RestaurantDao;
import com.codecool.restauratio.models.Restaurant;

import java.util.List;
import java.util.Map;

public class RestaurantService {

    RestaurantDao restdao = new RestaurantDao();

    //TODO
    void makeReservation (Map<String, Object> requestParams) {

    }

    public List getRestaurants () {
        List <Restaurant> restaurantList = null;
        System.out.println("Processed");
        try {
            restaurantList = restdao.getAll();
            return restaurantList;
        } catch (ConnectToDBFailed e) {
            System.out.println("Connection to db failed");
            return restaurantList;
        }


    }

}
