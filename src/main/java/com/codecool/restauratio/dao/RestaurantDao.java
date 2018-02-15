package com.codecool.restauratio.dao;


import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RestaurantDao extends Dao {

    private EntityManager em;

    public RestaurantDao() {
        this.em = DatabaseUtility.getEntityManager("restaurantPU");
    }

    RestaurantDao(EntityManager em) {
        this.em = em;
    }

    public void transactionProcess(Restaurant restaurant, String name) throws ConnectToDBFailed, NullPointerException {
        super.transactionProcess(restaurant, name, this, em);
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
    void add(Restaurant restaurant) {
        em.persist(restaurant);
    }

    @TransactionAnnotation
    void remove(Restaurant restaurant) throws ConnectToDBFailed {
        em.remove(restaurant);
    }

    public List<Restaurant> getByLocation(String location) throws ConnectToDBFailed {
        try {
            Query query = em.createNamedQuery("getRestaurantsByLocation");
            query.setParameter("location", location);
            return (List<Restaurant>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public List<String> getAllLocations() {
        Query query = em.createNamedQuery("getRestaurantsAllDistinctLocation");
        return (List<String>) query.getResultList();
    }


}
