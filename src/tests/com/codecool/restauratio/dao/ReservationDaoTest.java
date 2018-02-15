package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDaoTest {
    private static EntityManager em;
    private static ReservationDao reservationDao;

    @BeforeAll
    static void populateDb() {
        //initializing date
        Date date = new Date();
        //initializing test food and list for restaurant creation
        Food testFood = new Food("testFood1", 10, "testIngredients1", "testReview1");
        ArrayList<Food> testFoodList = new ArrayList<>(Collections.singletonList(testFood));
        //initializing test owner
        User testOwner = new User("testOwner", "testOwnerPw", false, true);
        //initializing test restaurant
        Restaurant testRestaurant = new Restaurant("testRestaurant", "testRestaurantDescription", "testAddress",
                testFoodList, 15, testOwner, "imageReference");
        //initializing test user who reserves
        User testUser = new User("testOrderUser", "testOrderUserPw", false, false);
        //initializing test reservations
        Reservation firstTestReservation = new Reservation(date, 2, testRestaurant, testUser);
        Reservation secondTestReservation = new Reservation(date, 10, testRestaurant, testUser);
        //persisting reservations and it dependencies to test database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testRestaurantPU");
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(testFood);
        em.persist(testOwner);
        em.persist(testRestaurant);
        em.persist(testUser);
        em.persist(firstTestReservation);
        em.persist(secondTestReservation);
        transaction.commit();
        //initializing reservation dao
        reservationDao = new ReservationDao(em);
    }

    @AfterAll
    static void CloseEm() {
        em.close();
    }

    @Test
    void getAll() throws ConnectToDBFailed {
        List<Reservation> orders = reservationDao.getAll();
        assertEquals(2, orders.size());
    }

    @Test
    void getById() throws ConnectToDBFailed {
        Reservation reservation = reservationDao.getById(1);
        assertNotNull(reservation);
    }

    @Test
    void add() throws ConnectToDBFailed, ParseException, NoSuchMethodException {
        Reservation reservation = reservationDao.getById(1);
        reservation.setNumberOfPeople(4);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse("2018-03-26 20:30");
        reservation.setCreationDate(date);
        reservationDao.transactionProcess(reservation, "add");
        //reservationDao.add(reservation);
        List<Reservation> reservations = reservationDao.getAll();
        assertTrue(reservations.contains(reservation));
    }

    @Test
    void remove() throws ConnectToDBFailed, NoSuchMethodException {
        Reservation reservation = reservationDao.getById(2);
        reservationDao.transactionProcess(reservation, "remove");
        //reservationDao.remove(reservation);
        List<Reservation> reservations = reservationDao.getAll();
        assertFalse(reservations.contains(reservation));
    }
}