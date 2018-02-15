package com.codecool.restauratio;

import com.codecool.restauratio.dao.*;
import com.codecool.restauratio.models.RestaurantTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        FoodDaoTest.class,
        OrderDaoTest.class,
        ReservationDaoTest.class,
        RestaurantDaoTest.class,
        UserDaoTest.class,
        RestaurantTest.class })
public class TestSuit {
}
