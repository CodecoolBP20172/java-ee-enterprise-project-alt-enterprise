package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantDaoTest {
    private static EntityManager em;
    private static RestaurantDao restaurantDao;
    private static Restaurant testRestaurant;

    @BeforeAll
    static void prepareDB() {
        // DB variables initialize
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testRestaurantPU");
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // setting up testdata
        ArrayList<Food> testFoodList = new ArrayList<>();
        Food testFood1 = new Food("testFood1", 10, "testIngredients1", "testReview1");
        testFoodList.add(testFood1);
        User testOwner = new User("testOwner", "testOwnerPw", false, true);
        testRestaurant = new Restaurant("testRestaurant",
                "testRestaurantDescription",
                "1037 Lajos Utca 27.",
                testFoodList,
                15,
                testOwner);

        // persisting testdata
        transaction.begin();
        em.persist(testFood1);
        em.persist(testOwner);
        em.persist(testRestaurant);
        transaction.commit();
//        em.close();
        restaurantDao = new RestaurantDao(em);
    }

    @AfterAll
    static void CloseEm() {
        em.close();
    }

    @Test
    void getAll() throws ConnectToDBFailed {
        List<Restaurant> testList = restaurantDao.getAll();
        assertEquals(1,testList.size());
    }

    @Test
    void getById() throws ConnectToDBFailed {
        List<Restaurant> testList = restaurantDao.getAll();
        Restaurant testRestaurant = restaurantDao.getById(1);
        assertEquals(testList.get(0).getId(), testRestaurant.getId());
    }

    @Test
    void add() throws ConnectToDBFailed, NoSuchMethodException {
        testRestaurant.setName("BabaKaja");
        restaurantDao.transactionProcess(testRestaurant, "add");
        //restaurantDao.add(testRestaurant);
        assertTrue(restaurantDao.getAll().contains(testRestaurant));
    }

    @Test
    void remove() throws ConnectToDBFailed, NoSuchMethodException {
        ArrayList<Food> testFoodList = new ArrayList<>();
        Food testFood1 = new Food("testFood1", 10, "testIngredients1", "testReview1");
        testFoodList.add(testFood1);
        User testOwner = new User("testOwner", "testOwnerPw", false, true);
        Restaurant testRestaurant = new Restaurant("testRestaurant",
                "testRestaurantDescription",
                "1037 Lajos Utca 27.",
                testFoodList,
                15,
                testOwner);
        restaurantDao.transactionProcess(testRestaurant, "remove");
        //restaurantDao.remove(testRestaurant);
        assertFalse(restaurantDao.getAll().contains(testRestaurant));
    }
}