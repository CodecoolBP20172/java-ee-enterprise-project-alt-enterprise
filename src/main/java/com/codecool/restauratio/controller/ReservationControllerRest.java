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
import java.util.Map;

@RestController
public class ReservationControllerRest {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/api/make_reservation", method = RequestMethod.POST)
    public ResponseEntity<String> makeReservation(@RequestBody Map<String, String> data, HttpSession session) throws ConnectToDBFailed {
        int userId = (int) session.getAttribute("id");
        System.out.println(userId);
        restaurantService.makeReservation(Date.valueOf(data.get("date")),
                Integer.valueOf(data.get("numOfPeople")), Integer.valueOf(data.get("restaurantId")), userId, data.get("comment"));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
