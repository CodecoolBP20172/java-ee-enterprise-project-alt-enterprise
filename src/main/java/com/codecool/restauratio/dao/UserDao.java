package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;
import java.util.List;

public class UserDao extends Dao{
    private EntityManager em;

    public UserDao() {
        em = DatabaseUtility.getEntityManager("restaurantPU");
    }

    UserDao(EntityManager em) {
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
//
//    public void transactionProcess(User user, String name) throws ConnectToDBFailed, NullPointerException {
//        Class obj = this.getClass();
//        Method m;
//        if(methodFinder(obj, name) != null) {
//            m = methodFinder(obj, name);
//        } else {
//            throw new NullPointerException("Method was not found");
//        }
//
//        try {
//            transaction.begin();
//            m.invoke(this, user);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ConnectToDBFailed(e.getMessage());
//        }
//    }


    public void transactionProcess(User user, String name) throws ConnectToDBFailed, NullPointerException, NoSuchMethodException {
        Method m = null;
        m = methodFinder(this.getClass(), name);
        super.transactionProcess(user, m, this, em);
    }

    public List<User> getAll() throws ConnectToDBFailed {
        try {
            return em.createNamedQuery("getAllUser").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public User getById(int userId) throws ConnectToDBFailed {
        try {
            return em.find(User.class, userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    @TransactionAnnotation
    public void add(User user) throws ConnectToDBFailed {
        em.persist(user);
    }

    @TransactionAnnotation
    public void remove(User user) throws ConnectToDBFailed {
        em.remove(user);
    }
}
