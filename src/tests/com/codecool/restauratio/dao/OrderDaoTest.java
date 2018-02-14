package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.DatabaseUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDaoTest {
    private static EntityManager em;
    private static OrderDao orderDao;
    private static Order testOrder;

    @BeforeAll
    static void populateTestDB() {
        //initializing date
        Date date = new Date();
        //initializing test foods and list for restaurant creation
        ArrayList<Food> testFoodList = new ArrayList<>();
        Food testFood1 = new Food("testFood1", 10, "testIngredients1", "testReview1");
        Food testFood2 = new Food("testFood2", 20, "testIngredients2", "testReview2");
        Food testFood3 = new Food("testFood3", 30, "testIngredients3", "testReview3");
        testFoodList.add(testFood1);
        testFoodList.add(testFood2);
        testFoodList.add(testFood3);
        //initializing test restaurant
        User testOwner = new User("testOwner", "testOwnerPw", false, true);
        Restaurant testRestaurant = new Restaurant("testRestaurant",
                "testRestaurantDescription",
                "1037 Lajos Utca 27.",
                testFoodList,
                15,
                testOwner);
        //initializing user address (user input originally)
        String testAddress = "1037 Lajos Utca 28.";
        //initializing food list for order
        List<Food> testOrderFoodList = new ArrayList<>();
        testOrderFoodList.add(testFood1);
        testOrderFoodList.add(testFood2);
        //initializing test user who orders
        User testOrderUser = new User("testOrderUser",
                "testOrderUserPw",
                false,
                false);
        //initializing test order
        testOrder = new Order(date, testAddress, testOrderFoodList, testOrderUser, testRestaurant);
        //persisting order to test database
        em = DatabaseUtility.getEntityManager("testRestaurantPU");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(testFood1);
        em.persist(testFood2);
        em.persist(testFood3);
        em.persist(testOwner);
        em.persist(testOrderUser);
        em.persist(testRestaurant);
        em.persist(testOrder);
        transaction.commit();
        //initializing order dao
        orderDao = new OrderDao(em);
    }

    @Test
    void getAll() throws ConnectToDBFailed {
        List<Order> order = orderDao.getAll();
        assertEquals(1,order.get(0).getId());
    }

    @Test
    void getById() throws ConnectToDBFailed {
        Order order = orderDao.getById(1);
        assertNotNull(order);
    }

    @Test
    void add() throws ConnectToDBFailed {
        testOrder.setAddress("Test street 13.");
        orderDao.add(testOrder);
        List<Order> orders= orderDao.getAll();
        assertTrue(orders.contains(testOrder));
    }

    @Test
    void remove() throws ConnectToDBFailed {
        int originalOrdersLength =  orderDao.getAll().size();
        orderDao.remove(testOrder);
        int modifiedOrdersLength = orderDao.getAll().size();
        assertEquals(originalOrdersLength-1, modifiedOrdersLength );
    }
}
