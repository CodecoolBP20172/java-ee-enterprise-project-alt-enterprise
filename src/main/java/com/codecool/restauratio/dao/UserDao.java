package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDao {
    private static EntityManager em = DatabaseUtility.getEntityManager();

    public List<User> getAll() throws ConnectToDBFailed {
        try {
            return em.createNamedQuery("getAllUser").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public User getById(long userId) throws ConnectToDBFailed {
        try {
            return em.find(User.class, userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void add(User user) throws ConnectToDBFailed {
        try {
            em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void remove(User user) throws ConnectToDBFailed {
        try {
            em.remove(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }
}
