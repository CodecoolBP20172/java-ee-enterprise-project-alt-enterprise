package com.codecool.restauratio.dao;

import com.codecool.restauratio.customException.ConnectToDBFailed;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ReservationDao {
    private EntityManager em;
    private EntityTransaction transaction;

    public ReservationDao() {
        this.em  = DatabaseUtility.getEntityManager("restaurantPU");
        this.transaction = em.getTransaction();
    }

    public ReservationDao(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
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

    public void add(Reservation reservation) throws ConnectToDBFailed {
        try {
            em.persist(reservation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

    public void remove(Reservation reservation) throws ConnectToDBFailed {
        try {
            em.remove(reservation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectToDBFailed(e.getMessage());
        }
    }

}
