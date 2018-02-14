package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.utils.DatabaseUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodDaoTest {
    private static EntityManager em;
    private static FoodDao foodDao;

    @BeforeAll
    static void populateDB() {
        em = DatabaseUtility.getEntityManager("testRestaurantPU");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Food pizza = new Food("Pizza", 12.0, "ingredients", "review");
        Food fries = new Food("frenchFries", 7.0, "potato", "fine");
        em.persist(pizza);
        em.persist(fries);
        transaction.commit();
        foodDao = new FoodDao(em);
    }

    @Test
    void getAll() throws ConnectToDBFailed {
        List<Food> foods = foodDao.getAll();
        assertEquals("Pizza",foods.get(0).getName());
    }

    @Test
    void getById() throws ConnectToDBFailed {
        int pizzaId = 1;
        Food food = foodDao.getById(pizzaId);
        assertNotNull(food);
    }

    @Test
    void add() throws ConnectToDBFailed {
        Food newFood = new Food("Hamburger", 10.0, "Ham", "good");
        foodDao.add(newFood);
        List<Food> foods = foodDao.getAll();
        assertTrue(foods.contains(newFood));

    }

    @Test
    void remove() throws ConnectToDBFailed {
        Food fries = foodDao.getById(2);
        foodDao.remove(fries);
        List<Food> foods = foodDao.getAll();
        assertFalse(foods.contains(fries));
    }
}