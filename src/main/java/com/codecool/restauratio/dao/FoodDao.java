package com.codecool.restauratio.dao;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FoodDao {

    public List getAll(){
        EntityManager em = DatabaseUtility.getEntityManager();
        return em.createNamedQuery("getAllFood").getResultList();
    }

    public Food getFoodById(long id) {
        EntityManager em = DatabaseUtility.getEntityManager();
        TypedQuery<Food> query = em.createNamedQuery("getFoodById", Food.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public void addFood(Food food) {
        EntityManager em = DatabaseUtility.getEntityManager();
        em.persist(food);
    }

    public void removeFood(Food food) {
        EntityManager em = DatabaseUtility.getEntityManager();
        em.remove(food);
    }
}
