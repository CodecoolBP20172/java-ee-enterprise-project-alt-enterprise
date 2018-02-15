package com.codecool.restauratio.dao;


import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.Method;
import java.util.List;

public class RestaurantDao extends Dao{

    private EntityManager em;

    public RestaurantDao() {
        this.em  = DatabaseUtility.getEntityManager("restaurantPU");
    }

    public RestaurantDao(EntityManager em) {
        this.em = em;
    }

    private Method methodFinder(Class obj, String name) {
        for (Method method : obj.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(TransactionAnnotation.class)) {
                continue;
            }
            if (method.isAnnotationPresent(TransactionAnnotation.class) & method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    public void transactionProcess(Restaurant restaurant, String name) throws ConnectToDBFailed, NullPointerException, NoSuchMethodException {
        Method m = null;
        m = methodFinder(this.getClass(), name);
        super.transactionProcess(restaurant, m, this, em);
    }

    public List<Restaurant> getAll() throws ConnectToDBFailed {
        try {
            return em.createNamedQuery("getAllRestaurants").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public Restaurant getById(int id) throws ConnectToDBFailed {
        try {
            return em.find(Restaurant.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    @TransactionAnnotation
    public void add(Restaurant restaurant) throws ConnectToDBFailed {
        em.persist(restaurant);
    }

    @TransactionAnnotation
    public void remove(Restaurant restaurant) throws ConnectToDBFailed {
        em.remove(restaurant);
    }
}
