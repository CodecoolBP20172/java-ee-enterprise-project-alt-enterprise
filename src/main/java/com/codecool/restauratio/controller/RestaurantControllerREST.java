package com.codecool.restauratio.controller;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Date;
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

    @RequestMapping(value = "/api/get_restaurant_by_type", method = RequestMethod.POST)
    public ResponseEntity<List<Restaurant>> restaurantBrowseByType(@RequestBody Map<String, String> data) {
        return new ResponseEntity<>(restService.restaurantTypeBrowser(data.get("description")), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/make_reservation", method = RequestMethod.POST)
    public ResponseEntity<String> makeReservation(@RequestBody Map<String, String> data, HttpSession session) throws ConnectToDBFailed {
        int userId = (int) session.getAttribute("id");
        restService.makeReservation(Date.valueOf(data.get("date")),
                Integer.valueOf(data.get("numOfPeople")), Integer.valueOf(data.get("restaurantId")), userId, data.get("comment"));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
