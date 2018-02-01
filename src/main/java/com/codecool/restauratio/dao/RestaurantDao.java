package com.codecool.restauratio.dao;


import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import java.util.List;

public class RestaurantDao {

    public List getAll() {
        EntityManager em = DatabaseUtility.getEntityManager();
        return em.createNamedQuery("getAllRestaurants").getResultList();
    }
}
