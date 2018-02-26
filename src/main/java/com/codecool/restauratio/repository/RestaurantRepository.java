package com.codecool.restauratio.repository;

import com.codecool.restauratio.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> getRestaurantByLocation(String location);

    @Query("SELECT DISTINCT location FROM restaurant")
    List<String> getRestaurantLocations();
}
