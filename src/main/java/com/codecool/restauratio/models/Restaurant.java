package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;
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
    private long restaurant_id;
    private String name;
    private String description;
    private String location;

    @ManyToMany
    @JoinTable(
            name = "Restaurant_Food",
            joinColumns = { @JoinColumn(name = "restaurant_id")},
            inverseJoinColumns = { @JoinColumn(name = "food_id")}
    )
    private List<Food> menu;
    private long capacity;
    private boolean isAvailable;

    @OneToMany(mappedBy = "reservationRestaurant")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "orderRestaurant")
    private List<Order> orders;

    @ElementCollection
    @CollectionTable(name="Reviews")
    private List<String> reviews;

    @ElementCollection
    @CollectionTable(name="Ratings")
    private List<Integer> ratings;

    @ManyToOne
    private User owner;

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

    public Restaurant() {
    }

//    public void addFoodToMenu(Food food) {
//        if (!menu.contains(food)) {
//            menu.add(food);
//        }
//    }
    
    long getRestaurant_id() {
        return restaurant_id;
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

//    List<Food> getMenu() {
//        return menu;
//    }

    long getCapacity() {
        return capacity;
    }

    boolean isAvailable() {
       return isAvailable;
    }

    List<String> getReview() {
        return reviews;
    }

    int getAverageRating() {
        int sum = 0;
        for (Integer rating:ratings) {
            sum += rating;
        }
        return sum/ratings.size();
    }

//    void setMenu(List<Food> menu) {
//        this.menu = menu;
//    }

    void setAvailable(boolean available) {
        isAvailable = available;
    }

    void addReview(String review) {
        this.reviews.add(review);
    }

    void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw  new IllegalArgumentException("Rating must be between 1 and 5!");
        }
        ratings.add(rating);
    }
}
