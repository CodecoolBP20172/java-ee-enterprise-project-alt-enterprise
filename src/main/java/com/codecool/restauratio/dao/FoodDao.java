package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class FoodDao {
    private static EntityManager em = DatabaseUtility.getEntityManager();
    private static EntityTransaction transaction = em.getTransaction();

    public List<Food> getAll() throws ConnectToDBFailed {
        try {
            return em.createNamedQuery("getAllFood").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public Food getById(long id) throws ConnectToDBFailed {
        try {
            TypedQuery<Food> query = em.createNamedQuery("getFoodById", Food.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void add(Food food) throws ConnectToDBFailed {
        try {
            transaction.begin();
            em.persist(food);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }


    public void remove(Food food) throws ConnectToDBFailed {
        try {
            em.remove(food);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }
}