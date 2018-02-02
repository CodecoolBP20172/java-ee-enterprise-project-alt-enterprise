package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.RestaurantDao;
import com.codecool.restauratio.models.Restaurant;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantController {

    public static ModelAndView renderRestaurants(Request req, Response res) throws ConnectToDBFailed {
        Map<String, Object> params = new HashMap<>();
        RestaurantDao restdao = new RestaurantDao();
        List<Restaurant> restaurantList = restdao.getAll();
        params.put("restaurants", restaurantList);
        // to be loaded with restaurant object
        return new ModelAndView(params, "restaurants");
    }

}
