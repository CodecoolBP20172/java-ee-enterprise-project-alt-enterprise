package com.codecool.restauratio.dao;

import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao {
    public List getAll() {
        EntityManager em = DatabaseUtility.getEntityManager();
        return em.createNamedQuery("getAllUser").getResultList();
    }
    public User getUserById(Integer userId) {
        EntityManager em = DatabaseUtility.getEntityManager();
        return em.find(User.class,1);
    }
    public String getUserPasswordById(Integer userId) {
        EntityManager em = DatabaseUtility.getEntityManager();
        User currentUser= em.find(User.class,1);
        return currentUser.getPassword();
    }
}
