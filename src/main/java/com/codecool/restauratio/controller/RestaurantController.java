package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.RestaurantDao;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.services.RestaurantService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantController {

    private static RestaurantService restService = new RestaurantService ();

    public static ModelAndView renderRestaurants(Request req, Response res) throws ConnectToDBFailed {
        Map<String, Object> params = new HashMap<>();
        params.put("restaurants", restService.getRestaurants());
        // to be loaded with restaurant object
        return new ModelAndView(params, "restaurants");
    }

    public static void makeReservationAtRestaurant(Request req, Response res) throws ConnectToDBFailed {

    }

}
