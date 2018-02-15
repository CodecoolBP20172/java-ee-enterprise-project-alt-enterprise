package com.codecool.restauratio.dao;


import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RestaurantDao {

    private static EntityManager em = DatabaseUtility.getEntityManager();

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

    public List<Restaurant> getByLocation(String location) throws ConnectToDBFailed {
        try {
            System.out.println(location);
            Query query = em.createNamedQuery("getRestaurantsByLocation");
            query.setParameter("location", location);
            return (List<Restaurant>) query.getResultList();
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
