package com.codecool.restauratio.models;

public class Food {
    private int id;
    private int price;
    private String name;
    private String ingredients;
    private String review;
    //this is a placeholder below until we decide if we wanna use javax.imageio package or some other way to store images
    private String picture;

    private Food() {
        this.id = 0;
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
