package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.services.RestaurantService;
import com.codecool.restauratio.utils.JsonHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class RestaurantController {

    private static RestaurantService restService = new RestaurantService ();

    public static ModelAndView renderRestaurants(Request req, Response res) throws ConnectToDBFailed {
        Map<String, Object> params = new HashMap<>();

        params.put("username", req.session().attribute("username"));
        params.put("loggedin", req.session().attribute("id") != null);
        params.put("restaurants", restService.getRestaurants());
        // to be loaded with restaurant object
        return new ModelAndView(params, "restaurants");
    }

    public static void makeReservationAtRestaurant(Request req, Response res) throws ConnectToDBFailed {

    }

    public static String restaurantBrowseByLocation(Request request, Response response) {
        System.out.println(request);
        Map<String, String> data = JsonHandler.parseJson(request);
        String targetLocation = data.get("location");
        return restService.restaurantLocationBrowser(targetLocation);
    }
}
