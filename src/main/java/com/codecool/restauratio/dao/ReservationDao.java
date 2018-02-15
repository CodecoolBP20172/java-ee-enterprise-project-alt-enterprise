package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.dao.transactionAnnotation.TransactionAnnotation;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.Method;
import java.util.List;

public class ReservationDao extends Dao{
    private EntityManager em;

    public ReservationDao() {
        this.em  = DatabaseUtility.getEntityManager("restaurantPU");
    }

    public ReservationDao(EntityManager em) {
        this.em = em;
    }

    public void transactionProcess(Reservation reservation, String name) throws ConnectToDBFailed, NullPointerException, NoSuchMethodException {
        super.transactionProcess(reservation, name, this, em);
    }

    public List<Reservation> getAll() throws ConnectToDBFailed {
        try {
            return em.createNamedQuery("getAllReservations").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public Reservation getById(Integer orderId) throws ConnectToDBFailed {
        try {
            return em.find(Reservation.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    @TransactionAnnotation
    void add(Reservation reservation) throws ConnectToDBFailed {
            em.persist(reservation);
    }

    @TransactionAnnotation
    void remove(Reservation reservation) throws ConnectToDBFailed {
            em.remove(reservation);
    }

}
