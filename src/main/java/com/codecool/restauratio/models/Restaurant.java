package com.codecool.restauratio.models;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllRestaurants",
                query = "SELECT Restaurant FROM Restaurant"),
        @NamedQuery(
                name = "getRestaurantById",
                query = "select Restaurant FROM Restaurant where Restaurant.id = :id")
})
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String location;


    private List<Food> menu;
    private int capacity;
    private boolean isAvailable;
    private List<String> review;
    private List<Integer> ratings;

    public Restaurant() {
    }

    public Restaurant(String name, String description, String location, List<Food> menu, int capacity) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.menu = menu;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    public void addFoodToMenu(Food food) {
        if (!menu.contains(food)) {
            menu.add(food);
        }
    }
    
    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    String getLocation() {
        return location;
    }

    List<Food> getMenu() {
        return menu;
    }

    int getCapacity() {
        return capacity;
    }

    boolean isAvailable() {
       return isAvailable;
    }

    List<String> getReview() {
        return review;
    }

    int getAverageRating() {
        int sum = 0;
        for (Integer rating:ratings) {
            sum += rating;
        }
        return sum/ratings.size();
    }

    void setMenu(List<Food> menu) {
        this.menu = menu;
    }

    void setAvailable(boolean available) {
        isAvailable = available;
    }

    void addReview(String review) {
        this.review.add(review);
    }

    void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw  new IllegalArgumentException("Rating must be between 1 and 5!");
        }
        ratings.add(rating);
    }
}
