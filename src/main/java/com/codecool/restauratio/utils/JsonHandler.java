package com.codecool.restauratio.utils;

import com.codecool.restauratio.models.Restaurant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonHandler {

//    public static Map<String, String> parseJson(Request request) {
//        Gson gson = new Gson();
//        Type type = new TypeToken<Map<String, String>>() {
//        }.getType();
//        Map<String, String> out = gson.fromJson(request.body(), type);
//        return out;
//    }

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    //TODO List<Food> return for Json response has to be written
    public static List<Map> restaurantModel(List<Restaurant> restaurantList) {
        List<Map> model = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            Map<String, String> currentRestaurant = new HashMap<>();
            currentRestaurant.put("id", Integer.toString((int)restaurant.getId()));
            currentRestaurant.put("name", restaurant.getName());
            currentRestaurant.put("description", restaurant.getDescription());
            currentRestaurant.put("location", restaurant.getLocation());
            currentRestaurant.put("capacity", Integer.toString((int)restaurant.getCapacity()));
            currentRestaurant.put("imageReference", restaurant.getImageReference());
            model.add(currentRestaurant);
        }

        return model;
    }
}
