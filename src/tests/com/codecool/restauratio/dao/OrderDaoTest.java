package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Order;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDaoTest {
    private static EntityManager em;
    private static OrderDao orderDao;

    @BeforeAll
    static void populateTestDB() {
        //initializing date
        Date date = new Date();
        //initializing test foods and list for restaurant creation
        Food testFood1 = new Food("testFood1", 10, "testIngredients1", "testReview1");
        Food testFood2 = new Food("testFood2", 20, "testIngredients2", "testReview2");
        Food testFood3 = new Food("testFood3", 30, "testIngredients3", "testReview3");
        //initializing test owner
        ArrayList<Food> testFoodList = new ArrayList<>(Arrays.asList(testFood1, testFood2, testFood3));
        User testOwner = new User("testOwner", "testOwnerPw", false, true);
        //initializing test restaurant
        Restaurant testRestaurant = new Restaurant("testRestaurant",
                "testRestaurantDescription",
                "1037 Lajos Utca 27.",
                testFoodList,
                15,
                testOwner);
        //initializing user address (user input originally)
        String testAddress = "1037 Lajos Utca 28.";
        //initializing food list for order
        List<Food> firstTestOrderFoodList = new ArrayList<>(Arrays.asList(testFood1, testFood2));
        List<Food> secondTestOrderFoodList = new ArrayList<>(Arrays.asList(testFood1, testFood3));
        //initializing test user who orders
        User testOrderUser = new User("testOrderUser", "testOrderUserPw", false, false);
        //initializing test order
        Order firstTestOrder = new Order(date, testAddress, firstTestOrderFoodList, testOrderUser, testRestaurant);
        Order secondTestOrder = new Order(date, testAddress, secondTestOrderFoodList, testOrderUser, testRestaurant);
        //persisting order to test database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testRestaurantPU");
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(testFood1);
        em.persist(testFood2);
        em.persist(testFood3);
        em.persist(testOwner);
        em.persist(testRestaurant);
        em.persist(testOrderUser);
        em.persist(firstTestOrder);
        em.persist(secondTestOrder);
        transaction.commit();
        //initializing order dao
        orderDao = new OrderDao(em);
    }

    @AfterAll
    static void CloseEm() {
        em.close();
    }

    @Test
    void getAll() throws ConnectToDBFailed {
        List<Order> orders = orderDao.getAll();
        assertEquals(1, orders.get(0).getId());
    }

    @Test
    void getById() throws ConnectToDBFailed {
        assertNotNull(orderDao.getById(1));
    }

    @Test
    void add() throws ConnectToDBFailed, NoSuchMethodException {
        Order lastOrder = orderDao.getById(1);
        lastOrder.setAddress("Lófasz utca 13.");
        orderDao.transactionProcess(lastOrder, "add");
        //orderDao.add(lastOrder);
        List<Order> orders = orderDao.getAll();
        assertTrue(orders.contains(lastOrder));
    }

    @Test
    void remove() throws ConnectToDBFailed, NoSuchMethodException {
        Order lastOrder = orderDao.getById(2);
        orderDao.transactionProcess(lastOrder, "remove");
        //orderDao.remove(lastOrder);
        List<Order> orders = orderDao.getAll();
        assertFalse(orders.contains(lastOrder));
    }
}
