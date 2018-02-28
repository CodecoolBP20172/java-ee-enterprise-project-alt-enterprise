package com.codecool.restauratio.services;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.FoodRepository;
import com.codecool.restauratio.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InitializerBean {

    public InitializerBean(UserService userService, RestaurantService restaurantService,
                           ReservationRepository reservRepo, FoodRepository foodRepo) throws ParseException {

        User user1 = new User("józsi", "József", "Bende", "hurka", "józsi@citromail.com", "codecool", true, false);
        User user2 = new User("bodri", "Bodri", "Nemecsek", "mecska", "bodri@gmail.com", "home", true, true);

        Food f = new Food("Melák Menü", 1500, "szenya, rántotthus, rántottsajt", "király");
        Food f2 = new Food("buja burger", 200, "burger", "jó");
        Food f3 = new Food("Tele Tál Falafel", 4000, "minden ami blefér", "ragya");
        foodRepo.save(f);
        foodRepo.save(f2);
        foodRepo.save(f3);

        List<Food> list = new ArrayList<>();
        list.add(f);
        List<Food> list2 = new ArrayList<>();
        list2.add(f);
        list2.add(f2);
        List<Food> list3 = new ArrayList<>();
        list3.add(f);
        list3.add(f2);
        list3.add(f3);

        Restaurant r = new Restaurant("Halászcsárda", "good", "here", list, 100, user1, "/img/halasz_image.jpg");
        Restaurant r2 = new Restaurant("Csirkés", "pretty", "Mány", list2, 50, user2, "/img/csirkes_image.jpeg");
        Restaurant r3 = new Restaurant("Titiz", "bad", "Mány", list3, 10, user2, "/img/titiz_image.jpg");
        userService.registerUser(user1);
        userService.registerUser(user2);
        restaurantService.addRestaurant(r);
        restaurantService.addRestaurant(r2);
        restaurantService.addRestaurant(r3);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse("2018-03-01 11:45");
        Reservation reservation = new Reservation(date, 4, r2, user1);
        reservRepo.save(reservation);
    }
}
