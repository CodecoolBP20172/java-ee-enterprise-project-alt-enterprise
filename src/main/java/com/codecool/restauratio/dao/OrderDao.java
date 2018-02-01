package com.codecool.restauratio.dao;

import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class OrderDao {

    public List getAll() {
        EntityManager em = DatabaseUtility.getEntityManager();
        return em.createNamedQuery("getAllOrder").getResultList();
    }
    public Order getOrderById(Integer orderId) {
        EntityManager em = DatabaseUtility.getEntityManager();
        Query queryOrdersById = em.createNamedQuery(
                "getOrderById"
        );
        queryOrdersById.setParameter("id", orderId);
        return (Order) queryOrdersById.getSingleResult();
    }
}
