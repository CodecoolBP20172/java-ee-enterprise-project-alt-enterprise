package com.codecool.restauratio.repository;

import com.codecool.restauratio.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> getRestaurantByLocation(String location);

    @Query("SELECT DISTINCT r.location FROM Restaurant r")
    List<String> getRestaurantLocations();

    List<Restaurant> getRestaurantByDescription(String description);

    @Query("SELECT DISTINCT r.description FROM Restaurant r ")
    List<String> getRestaurantDescriptions();
}
