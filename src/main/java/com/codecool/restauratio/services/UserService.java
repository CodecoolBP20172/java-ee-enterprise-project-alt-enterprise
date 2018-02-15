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
    public int registerUser(String userName, String psw, boolean isAdmin, boolean isOwner) throws ConnectToDBFailed, FailedDataVertification, NoSuchMethodException {
        User user = new User(userName, psw, isAdmin, isOwner);
        userDao.transactionProcess(user, "add");
        System.out.println("user registered in service");
//        User testUser = userDao.getById(user.getUserId());
//        if(testUser.getUserId() == user.getUserId()) {
//            return (int) user.getUserId();
//        } else {
//            throw new FailedDataVertification("User id in memory and in the database are not the same");
//        }
        return (int)user.getUserId();
    }

    // returns userId if the credential verification is successful else throws an exception
    public boolean login(String userName, String psw) throws ConnectToDBFailed, FailedDataVertification {
        List<User> allUsers = userDao.getAll();
        for (User currentUser : allUsers) {
            if (currentUser.getUserName().equals(userName) & currentUser.checkPassword(psw)) {
                return true;
            }
        }
        throw new FailedDataVertification("Wrong username or password");
    }

    // returns the id of the created restaurant pared with this user
    private int newRestaurant(String name, String description, String location, List<Food> menu, int capacity, int userId) throws ConnectToDBFailed, FailedDataVertification, NoSuchMethodException {
        User owner = userDao.getById(userId);
        Restaurant restaurant = new Restaurant(name, description, location, menu, capacity, owner);
        RestaurantDao restaurantDao = new RestaurantDao();
        restaurantDao.transactionProcess(restaurant, "add");
        Restaurant test = restaurantDao.getById(restaurant.getId());
        if(test.getId() == restaurant.getId()) {
            return (int) restaurant.getId();
        } else {
            throw new FailedDataVertification("Restaurant id in memory and in database are not the same");
        }
    }

    public int getUserId(String username) throws ConnectToDBFailed, FailedDataVertification {
        for (User user: userDao.getAll()){
            if (user.getUserName().equals(username)){
                return (int)user.getUserId();
            }
        }
        throw new FailedDataVertification("Username or password incorrect");
    }

    // returns a boolean that indicates whether the user exists or not
    public boolean isUserExist(String username) throws ConnectToDBFailed {
        for (User user : userDao.getAll()){
            if (user.getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }

    private boolean changePassword(int useriD, String newPsw, String newPsw2){ return true;}

    private void deleteRestaurant(int userId, int restaurantId) {}
}
