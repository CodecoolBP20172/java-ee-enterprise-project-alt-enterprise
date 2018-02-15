package com.codecool.restauratio.models;

import com.codecool.restauratio.models.users.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RestaurantTest {
    private static Restaurant restaurant;

    @BeforeAll
    static void setUp() {
        Food FoodMock = mock(Food.class);
        User userMock = mock(User.class);
        restaurant = new Restaurant("restaurant", "description", "address", new ArrayList<>(Collections.singletonList(FoodMock)), 2, userMock);
    }

    @Test
    void addFoodToMenu() {
        Food anotherFoodMock = mock(Food.class);
        restaurant.addFoodToMenu(anotherFoodMock);
        List<Food> menu = restaurant.getMenu();
        assertTrue(menu.contains(anotherFoodMock));
    }

    @Test
    void getAverageRating() {
        for (int i = 1; i < 6; i++) {
            restaurant.addRating(i);
        }
        assertEquals(3, restaurant.getAverageRating(), 0.1);
    }
}