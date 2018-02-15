package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.Method;

public abstract class Dao {
    private EntityManager em;
    private EntityTransaction transaction;

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

    public void transactionProcess(Object objectToProcess, String name, Dao dao) throws ConnectToDBFailed, NullPointerException {
        Method m;
        this.em = dao.getEm();
        this.transaction = dao.getTransaction();
        // Method method = getClass().getMethod("someHandlingMethod", obj.getClass());
        if(methodFinder(dao.getClass(), name) != null) {
            m = methodFinder(dao.getClass(), name);
        } else {
            throw new NullPointerException("Method was not found");
        }

        try {
            transaction.begin();
            m.invoke(dao, objectToProcess);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public EntityTransaction getTransaction() {
        return transaction;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setTransaction(EntityTransaction transaction) {
        this.transaction = transaction;
    }
}
