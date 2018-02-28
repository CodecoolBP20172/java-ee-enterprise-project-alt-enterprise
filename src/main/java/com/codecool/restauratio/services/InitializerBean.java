package com.codecool.restauratio.services;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.FoodRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializerBean {

    public InitializerBean(UserService userService, RestaurantService restaurantService, FoodRepository foodrepo) {

        User user1 = new User("józsi", "József", "Bende", "hurka", "józsi@citromail.com", true, false);
        User user2 = new User("bodri", "Bodri", "Nemecsek", "mecska", "bodri@gmail.com", true, true);

        Food f = new Food("Melák Menü", 1500, "szenya, rántotthus, rántottsajt", "király");
        Food f2 = new Food("buja burger", 200, "burger", "jó");
        Food f3 = new Food("Tele Tál Falafel", 4000, "minden ami blefér", "ragya");
        foodrepo.save(f);
        foodrepo.save(f2);
        foodrepo.save(f3);

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
    }
}
