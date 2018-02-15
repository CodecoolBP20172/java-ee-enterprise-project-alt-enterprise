package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.Method;

public abstract class Dao {

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

    public void transactionProcess(Object objectToProcess, String name, Dao dao, EntityManager em) throws ConnectToDBFailed, NullPointerException {
        EntityTransaction transaction = em.getTransaction();
        Method method = methodFinder(dao.getClass(), name);

        if (method == null) {
            throw new NullPointerException();
        }

        try {
            transaction.begin();
            method.invoke(dao, objectToProcess);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }
}
