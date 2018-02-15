package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.lang.reflect.Method;
import java.util.List;

public class FoodDao extends Dao{
    private EntityManager em;

    public FoodDao() {
        this.em  = DatabaseUtility.getEntityManager("restaurantPU");
    }

    FoodDao(EntityManager em) {
        this.em = em;
    }


    public void transactionProcess(Food food, String name) throws ConnectToDBFailed, NullPointerException, NoSuchMethodException {
        super.transactionProcess(food, name, this, em);
    }

    public List<Food> getAll() throws ConnectToDBFailed {
        try {
            return em.createNamedQuery("getAllFood").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public Food getById(int id) throws ConnectToDBFailed {
        try {
            TypedQuery<Food> query = em.createNamedQuery("getFoodById", Food.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    @TransactionAnnotation
    void add(Food food) throws ConnectToDBFailed {
        em.persist(food);
    }


    @TransactionAnnotation
    void remove(Food food) throws ConnectToDBFailed {
        em.remove(food);
    }
}
