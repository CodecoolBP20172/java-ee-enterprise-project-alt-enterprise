package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class OrderDao {
    private static EntityManager em = DatabaseUtility.getEntityManager("restaurantPU");
    private static EntityTransaction transaction;

    public OrderDao() {
    }

    public OrderDao(EntityManager em) {
        this.em = em;
        transaction = em.getTransaction();
    }

    public List<Order> getAll() throws ConnectToDBFailed {
        try {
            return em.createNamedQuery("getAllOrder").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public Order getById(Integer orderId) throws ConnectToDBFailed {
        try {
            return em.find(Order.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void add(Order order) throws ConnectToDBFailed {
        try {
            transaction.begin();
            em.persist(order);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void remove(Order order) throws ConnectToDBFailed {
        try {
            transaction.begin();
            em.remove(order);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }
}
