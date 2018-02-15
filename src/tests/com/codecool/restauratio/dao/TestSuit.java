package com.codecool.restauratio.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        FoodDaoTest.class,
        OrderDaoTest.class,
        ReservationDaoTest.class,
        RestaurantDaoTest.class,
        UserDaoTest.class })
public class TestSuit {
}
