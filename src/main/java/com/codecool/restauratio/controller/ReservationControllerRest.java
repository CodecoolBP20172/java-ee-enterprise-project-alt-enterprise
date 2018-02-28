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
public class ReservationControllerRest {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/api/make_reservation", method = RequestMethod.POST)
    public ResponseEntity<String> makeReservation(@RequestBody Map<String, String> data) {
        System.out.println("Date: " + data.get("date"));
        System.out.println("numofpeople: " + data.get("numOfPeople"));
        System.out.println("comment: " + data.get("comment"));
        System.out.println("restid: " + data.get("restaurantId"));
        return new ResponseEntity<>("hurka", HttpStatus.OK);
    }
}
