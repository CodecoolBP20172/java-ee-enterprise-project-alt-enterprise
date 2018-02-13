package com.codecool.restauratio.models;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllFood",
                query = "SELECT f FROM Food f"),
        @NamedQuery(
                name = "getFoodById",
                query = "select f FROM Food f where f.id = :id")
})
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String name;
    private String ingredients;
    private String review;
    //this is a placeholder below until we decide if we wanna use javax.imageio package or some other way to store images
    private String picture;

    protected Food() {
    }

    public Food(String name, double price, String ingredients, String review) {
        setName(name);
        setPrice(price);
        setIngredients(ingredients);
        setReview(review);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Food = " +
                " name: " + name +
                ", price: " + price;
    }
}
