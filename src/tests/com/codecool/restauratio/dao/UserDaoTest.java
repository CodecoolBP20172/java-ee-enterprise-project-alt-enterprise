package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.users.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    private static EntityManager em;
    private static UserDao userDao;

    @BeforeAll
    static void populateDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testRestaurantPU");
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        User admin = new User("admin", "iamtheadmin", true, false);
        User owner = new User("owner", "ihavearestaurant", false, true);
        em.persist(admin);
        em.persist(owner);
        transaction.commit();
        userDao = new UserDao(em);
    }

    @AfterAll
    static void CloseEm() {
        em.close();
    }

    @Test
    void getAll() throws ConnectToDBFailed {
        List<User> users = userDao.getAll();
        assertEquals(2, users.size());
    }

    @Test
    void getById() throws ConnectToDBFailed {
        int adminId = 1;
        User user = userDao.getById(adminId);
        assertTrue(user.isAdmin());
    }

    @Test
    void add() throws ConnectToDBFailed, NoSuchMethodException {
        User user = new User("user", "iamasimpleuser", false, false);
        userDao.transactionProcess(user, "add");
        //userDao.add(user);
        List<User> users = userDao.getAll();
        assertTrue(users.contains(user));
    }

    @Test
    void remove() throws ConnectToDBFailed, NoSuchMethodException {
        User owner = userDao.getById(2);
        userDao.transactionProcess(owner, "remove");
        //userDao.remove(owner);
        List<User> foods = userDao.getAll();
        assertFalse(foods.contains(owner));
    }
}