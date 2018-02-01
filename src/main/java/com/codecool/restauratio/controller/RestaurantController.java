package com.codecool.restauratio.controller;

import com.codecool.restauratio.dao.RestaurantDao;
import com.codecool.restauratio.models.Restaurant;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantController {

    public static ModelAndView renderRestaurants(Request req, Response res){
        Map params = new HashMap<>();
        RestaurantDao restdao = new RestaurantDao();
        List<Restaurant> restaurantList = restdao.getAll();
        for (Restaurant restaurant: restaurantList){
            System.out.println(restaurant.getName());
        }
        params.put("restaurants", restaurantList);
        // to be loaded with restaurant object
        return new ModelAndView(params, "restaurants");
    }

}
