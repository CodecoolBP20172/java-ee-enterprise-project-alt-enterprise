package com.codecool.restauratio.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class RestaurantController {

    public static ModelAndView renderRestaurants(Request req, Response res){
        Map params = new HashMap<>();
        // to be loaded with restaurant object
        return new ModelAndView(params, "restaurants");
    }

}
