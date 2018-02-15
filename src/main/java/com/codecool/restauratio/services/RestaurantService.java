package com.codecool.restauratio.services;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.ReservationDao;
import com.codecool.restauratio.dao.RestaurantDao;
import com.codecool.restauratio.dao.UserDao;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.JsonHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {

    RestaurantDao restDao = new RestaurantDao();
    UserDao usDao = new UserDao();
    ReservationDao reservDao = new ReservationDao();


    void makeReservation (int numberOfPPL, int restaurantId, int userId) throws ConnectToDBFailed {
        Date date = new Date();
        Restaurant reservationTargetRestaurant = restDao.getById(restaurantId);
        User reservationUser = usDao.getById(userId);
        Reservation currentReservation = new Reservation(date, numberOfPPL, reservationTargetRestaurant, reservationUser);
        reservDao.add(currentReservation);
    }

    public List<Restaurant> getRestaurants () {
        List <Restaurant> restaurantList = null;
        try {
            restaurantList = restDao.getAll();
            return restaurantList;
        } catch (ConnectToDBFailed e) {
            System.out.println("Connection to db failed");
            return restaurantList;
        }
    }


    public String restaurantLocationBrowser (String location) {
        List<Restaurant> restaurantList;
        try {
            restaurantList = restDao.getByLocation(location);
        } catch (ConnectToDBFailed e) {
            System.out.println("Connection to db failed");
            return null;
        }

        return JsonHandler.toJson(JsonHandler.restaurantModel(restaurantList));
    }

    public Restaurant getRestaurantId(int id) throws ConnectToDBFailed {
        return restDao.getById(id);
    }

    public List<String> getLocations () {
        return restDao.getAllLocations();
    }

}
