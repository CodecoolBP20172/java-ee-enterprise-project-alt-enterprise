package com.codecool.restauratio.services;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.ReservationRepository;
import com.codecool.restauratio.repository.RestaurantRepository;
import com.codecool.restauratio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    void addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }


    void makeReservation (int numberOfPPL, int restaurantId, int userId) throws ConnectToDBFailed {
        Date date = new Date();
        Restaurant reservationTargetRestaurant = restaurantRepository.findOne(restaurantId);
        User reservationUser = userRepository.findOne(userId);
        Reservation currentReservation = new Reservation(date, numberOfPPL, reservationTargetRestaurant, reservationUser);
        reservationRepository.save(currentReservation);
    }

    public List<Restaurant> getRestaurants () throws ConnectToDBFailed {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        if (restaurants == null) {
            throw new ConnectToDBFailed("Connection failed. Haha.");
        }
        return restaurants;
    }


    public List<Restaurant> restaurantLocationBrowser (String location) {
        return restaurantRepository.getRestaurantByLocation(location);
    }

    public Restaurant getRestaurantId(int id) {
        return restaurantRepository.findOne(id);
    }

    public List<String> getLocations () throws ConnectToDBFailed {
        List<String> locations = restaurantRepository.getRestaurantLocations();
        if (locations == null) {
            throw new ConnectToDBFailed("Connection failed. Haha.");
        }
        return locations;
    }

    public List<String> getDescriptions() throws ConnectToDBFailed {
        List<String> descriptions = restaurantRepository.getRestaurantDescriptions();
        if (descriptions == null) {
            throw new ConnectToDBFailed("CONNECTION FAILED FAM. HAHA");
        }
        System.out.println(descriptions);
        return descriptions;
    }

    public List<Restaurant> restaurantTypeBrowser(String description) {
        return restaurantRepository.getRestaurantByDescription(description);
    }
}
