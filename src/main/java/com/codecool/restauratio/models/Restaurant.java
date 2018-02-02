package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllRestaurants",
                query = "SELECT r FROM Restaurant r"),
})
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String location;

    @ManyToMany
    @JoinTable(
            name = "Restaurant_Food",
            joinColumns = {@JoinColumn(name = "restaurant_id")},
            inverseJoinColumns = {@JoinColumn(name = "food_id")}
    )
    private List<Food> menu;
    private long capacity;
    private boolean isAvailable;

    @OneToMany(mappedBy = "reservationRestaurant")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "orderRestaurant")
    private List<Order> orders;

    @ElementCollection
    @CollectionTable(name = "Reviews")
    private List<String> reviews;

    @ElementCollection
    @CollectionTable(name = "Ratings")
    private List<Integer> ratings;

    @ManyToOne
    private User owner;

    protected Restaurant() {
    }

    public Restaurant(String name, String description, String location, List<Food> menu, int capacity, User owner) {
        setName(name);
        setDescription(description);
        setLocation(location);
        setMenu(menu);
        setCapacity(capacity);
        setOwner(owner);
        this.isAvailable = true;
        this.ratings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public void addFoodToMenu(Food food) {
        if (!menu.contains(food)) {
            menu.add(food);
        }
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public void setMenu(List<Food> menu) {
        this.menu = menu;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isAvailable() {
       return isAvailable;
    }

    public List<String> getReview() {
        return reviews;
    }

    public void addReview(String review) {
        this.reviews.add(review);
    }

    public int getAverageRating() {
        if (ratings.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (Integer rating : ratings) {
            sum += rating;
        }
        return sum / ratings.size();
    }

    public void addRating(int rating) throws IllegalArgumentException {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5!");
        }
        ratings.add(rating);
    }


    @Override
    public String toString() {
        return "Restaurant = " +
                "name: " + name +
                ", description: " + description +
                ", location: " + location +
                ", capacity: " + capacity +
                ", isAvailable: " + isAvailable +
                ", ratings: " + getAverageRating() +
                ", owner: " + owner.getUserName();
    }
}
