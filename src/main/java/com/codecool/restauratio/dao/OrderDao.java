package com.codecool.restauratio.dao;

import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDao {

    public List getAll() {
        EntityManager em = DatabaseUtility.getEntityManager();
        return em.createNamedQuery("getAllOrder").getResultList();
    }
    public Order getOrderById(Integer orderId) {
        EntityManager em = DatabaseUtility.getEntityManager();
        TypedQuery<Order> queryOrdersById = em.createNamedQuery(
                "getOrderById", Order.class
        );
        queryOrdersById.setParameter("id", orderId);
        return queryOrdersById.getSingleResult();
    }
}
