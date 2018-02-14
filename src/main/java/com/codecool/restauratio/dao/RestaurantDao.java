package com.codecool.restauratio.dao;


import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RestaurantDao {

    private EntityManager em;
    private EntityTransaction transaction;

    public RestaurantDao() {
        this.em  = DatabaseUtility.getEntityManager("restaurantPU");
        this.transaction = em.getTransaction();
    }

    public RestaurantDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }
    public List<Restaurant> getAll() throws ConnectToDBFailed {
        try {
            return em.createNamedQuery("getAllRestaurants").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public Restaurant getById(long id) throws ConnectToDBFailed {
        try {
            return em.find(Restaurant.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void add(Restaurant restaurant) throws ConnectToDBFailed {
        try {
            em.persist(restaurant);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void remove(Restaurant restaurant) throws ConnectToDBFailed {
        try {
            em.remove(restaurant);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }
}
