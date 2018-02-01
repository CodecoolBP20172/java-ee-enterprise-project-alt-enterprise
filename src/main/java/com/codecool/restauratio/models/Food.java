package com.codecool.restauratio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int food_id;
    private int price;
    private String name;
    private String ingredients;
    private String review;
    //this is a placeholder below until we decide if we wanna use javax.imageio package or some other way to store images
    private String picture;

    public Food() {
        this.food_id = 0;
        this.price = 0;
        this.name = "";
        this.ingredients = "";
        this.review = "";
        this.picture = "";
    }

    public Food(int price, String name, String ingredients, String review) {
        this.price = price;
        this.name = name;
        this.ingredients = ingredients;
        this.review = review;
    }
}
