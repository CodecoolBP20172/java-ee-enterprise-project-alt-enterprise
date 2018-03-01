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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class ReservationControllerREST {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/api/make_reservation", method = RequestMethod.POST)
    public ResponseEntity<String> makeReservation(@RequestBody Map<String, String> data, HttpSession session) throws ConnectToDBFailed, ParseException {
        int userId = (int) session.getAttribute("id");
        String rawDate = data.get("date");
        String formattedDate = rawDate.replace("T", " ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(formattedDate);
        restaurantService.makeReservation(date,
                Integer.valueOf(data.get("numOfPeople")), Integer.valueOf(data.get("restaurantId")), userId, data.get("comment"));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
