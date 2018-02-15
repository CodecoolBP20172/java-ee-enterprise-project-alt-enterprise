package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Food;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodDaoTest {
    private static EntityManager em;
    private static FoodDao foodDao;

    @BeforeAll
    static void populateDB() {
        Food pizza = new Food("Pizza", 12.0, "ingredients", "review");
        Food fries = new Food("frenchFries", 7.0, "potato", "fine");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testRestaurantPU");
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(pizza);
        em.persist(fries);
        transaction.commit();
        foodDao = new FoodDao(em);
    }

    @AfterAll
    static void CloseEm() {
        em.close();
    }

    @Test
    void getAll() throws ConnectToDBFailed {
        List<Food> foods = foodDao.getAll();
        assertEquals("Pizza", foods.get(0).getName());
    }

    @Test
    void getById() throws ConnectToDBFailed {
        int pizzaId = 1;
        Food food = foodDao.getById(pizzaId);
        assertNotNull(food);
    }

    @Test
    void add() throws ConnectToDBFailed, NoSuchMethodException {
        Food newFood = new Food("Hamburger", 10.0, "Ham", "good");
        foodDao.transactionProcess(newFood, "add");
        //foodDao.add(newFood);
        List<Food> foods = foodDao.getAll();
        assertTrue(foods.contains(newFood));

    }

    @Test
    void remove() throws ConnectToDBFailed, NoSuchMethodException {
        Food fries = foodDao.getById(2);
        foodDao.transactionProcess(fries, "remove");
        //foodDao.remove(fries);
        List<Food> foods = foodDao.getAll();
        assertFalse(foods.contains(fries));
    }
}