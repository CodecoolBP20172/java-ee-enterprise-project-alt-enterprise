package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.Method;

public abstract class Dao {

    public void transactionProcess(Object objectToProcess, Method method, Dao dao, EntityManager em) throws ConnectToDBFailed, NullPointerException {
        EntityTransaction transaction = em.getTransaction();

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
