package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDao {
    private static EntityManager em = DatabaseUtility.getEntityManager();


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
            em.persist(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void remove(Order order) throws ConnectToDBFailed {
        try {
            em.remove(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }
}
