package com.codecool.restauratio.services;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.ReservationDao;
import com.codecool.restauratio.dao.RestaurantDao;
import com.codecool.restauratio.dao.UserDao;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;

import java.util.Date;
import java.util.List;

public class RestaurantService {

    RestaurantDao restDao = new RestaurantDao();
    UserDao usDao = new UserDao();
    ReservationDao reservDao = new ReservationDao();


    void makeReservation (int numberOfPPL, long restaurantId, int userId) throws ConnectToDBFailed {
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

    public Restaurant getRestaurantId(int id) throws ConnectToDBFailed {
        return restDao.getById(id);
    }

}
