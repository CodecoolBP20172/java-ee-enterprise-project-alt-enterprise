package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.Method;
import java.util.List;

public class OrderDao extends Dao{
    private EntityManager em;

    public OrderDao() {
        this.em  = DatabaseUtility.getEntityManager("restaurantPU");
    }

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    public void transactionProcess(Order order, String name) throws ConnectToDBFailed, NullPointerException, NoSuchMethodException {
        super.transactionProcess(order, name, this, em);
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

    @TransactionAnnotation
    void add(Order order) throws ConnectToDBFailed {
            em.persist(order);
    }

    @TransactionAnnotation
    void remove(Order order) throws ConnectToDBFailed {
            em.remove(order);
    }
}
