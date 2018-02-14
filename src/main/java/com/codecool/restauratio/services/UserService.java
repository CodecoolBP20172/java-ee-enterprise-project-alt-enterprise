package com.codecool.restauratio.services;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.customException.FailedDataVertification;
import com.codecool.restauratio.dao.RestaurantDao;
import com.codecool.restauratio.dao.UserDao;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    // returns with the id of the created user
    private int registerUser(String userName, String psw, boolean isAdmin, boolean isOwner) throws ConnectToDBFailed, FailedDataVertification {
        User user = new User(userName, psw, isAdmin, isOwner);
        userDao.add(user);
        User testUser = userDao.getById(user.getUserId());
        if(testUser.getUserId() == user.getUserId()) {
            return (int) user.getUserId();
        } else {
            throw new FailedDataVertification("User id in memory and in the database are not the same");
        }
    }

    // returns userId if the credential verification is successful else throws an exception
    private int login(String userName, String psw) throws ConnectToDBFailed, FailedDataVertification {
        List<User> allUsers = userDao.getAll();
        for (User currentUser : allUsers) {
            if (currentUser.getUserName().equals(userName) & currentUser.getPassword().equals(psw)) {
                return (int) currentUser.getUserId();
            }
        }
        throw new FailedDataVertification("Wrong username or password");
    }

    // returns the id of the created restaurant pared with this user
    private int newRestaurant(String name, String description, String location, List<Food> menu, int capacity, int userId) throws ConnectToDBFailed, FailedDataVertification {
        User owner = userDao.getById((long) userId);
        Restaurant restaurant = new Restaurant(name, description, location, menu, capacity, owner);
        RestaurantDao restaurantDao = new RestaurantDao();
        restaurantDao.add(restaurant);
        Restaurant test = restaurantDao.getById(restaurant.getId());
        if(test.getId() == restaurant.getId()) {
            return (int) restaurant.getId();
        } else {
            throw new FailedDataVertification("Restaurant id in memory and in database are not the same");
        }
    }

    private int getUserId(int id) throws ConnectToDBFailed {
        return (int) userDao.getById(id).getUserId();
    }

    private boolean changePassword(int useriD, String newPsw, String newPsw2){ return true;}

    private void deleteRestaurant(int userId, int restaurantId) {}
}
