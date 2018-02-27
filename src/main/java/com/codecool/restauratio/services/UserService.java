package com.codecool.restauratio.services;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.customException.FailedDataVertification;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.RestaurantRepository;
import com.codecool.restauratio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    // returns with the id of the created user
    public int registerUser(String userName, String psw, boolean isAdmin, boolean isOwner) {
        User user = new User(userName, psw, isAdmin, isOwner);
        userRepository.save(user);
        return user.getUserId();
    }
    public int registerUser(User user) {
        userRepository.save(user);
        return user.getUserId();
    }

    // returns userId if the credential verification is successful else throws an exception
    public boolean login(String userName, String psw) {
        List<User> allUsers = userRepository.findAll();
        for (User currentUser : allUsers) {
            if (currentUser.getUserName().equals(userName) & currentUser.checkPassword(psw)) {
                return true;
            }
        }
        return false;
    }

    public int getUserId(String username) throws FailedDataVertification {
        for (User user : userRepository.findAll()) {
            if (user.getUserName().equals(username)) {
                return user.getUserId();
            }
        }
        throw new FailedDataVertification("Username or password incorrect");
    }

    // returns a boolean that indicates whether the user exists or not
    public boolean doesUserExist(String username) {
        for (User user : userRepository.findAll()) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
