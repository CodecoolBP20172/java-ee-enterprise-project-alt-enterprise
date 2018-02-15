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

    private Method methodFinder(Class obj, String name) {
        for (Method method : obj.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(TransactionAnnotation.class)) {
                continue;
            }
            if (method.isAnnotationPresent(TransactionAnnotation.class) & method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    public void transactionProcess(Order order, String name) throws ConnectToDBFailed, NullPointerException, NoSuchMethodException {
        Method m = null;
        m = methodFinder(this.getClass(), name);
        super.transactionProcess(order, m, this, em);
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
    public void add(Order order) throws ConnectToDBFailed {
            em.persist(order);
    }

    @TransactionAnnotation
    public void remove(Order order) throws ConnectToDBFailed {
            em.remove(order);
    }
}
