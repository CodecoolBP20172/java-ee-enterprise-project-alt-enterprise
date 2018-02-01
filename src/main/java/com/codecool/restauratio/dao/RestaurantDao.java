package com.codecool.restauratio.dao;


import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RestaurantDao {

    public void add(Restaurant restaurant) {
        EntityManager em = DatabaseUtility.getEntityManager();
        em.persist(restaurant);
    }

    public List<Restaurant> getAll() {
        EntityManager em = DatabaseUtility.getEntityManager();
        return em.createNamedQuery("getAllRestaurants").getResultList();
    }

    public Restaurant getRestaurantById(long id) {
        EntityManager em = DatabaseUtility.getEntityManager();
        TypedQuery<Restaurant> query = em.createNamedQuery("getRestaurantById", Restaurant.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
