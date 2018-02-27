package com.codecool.restauratio.controller;

import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RestaurantControllerREST {

    @Autowired
    private RestaurantService restService;

    @RequestMapping(value = "/api/get_restaurant_by_location", method = RequestMethod.POST)
    public ResponseEntity<List<Restaurant>> restaurantBrowseByLocation(@RequestBody Map<String, String> data) {
        return new ResponseEntity<>(restService.restaurantLocationBrowser(data.get("location")), HttpStatus.OK);
    }

}
