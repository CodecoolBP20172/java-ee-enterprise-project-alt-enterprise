package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.utils.DatabaseUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodDaoTest {
    private static EntityManager em;
    private static FoodDao foodDao;

    @BeforeAll
    static void populateDB() {
        em = DatabaseUtility.getEntityManager("testRestaurantPU");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Food testFood = new Food("Pizza", 12.0, "ingredients", "review");
        em.persist(testFood);
        transaction.commit();
        foodDao = new FoodDao(em);
    }

    @Test
    void getAll() throws ConnectToDBFailed {
        List<Food> foods = foodDao.getAll();
        System.out.println(foods);
        assertEquals("Pizza",foods.get(0).getName());
    }

    @Test
    void getById() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }
}