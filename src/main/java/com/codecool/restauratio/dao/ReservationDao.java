package com.codecool.restauratio.dao;

import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.utils.DatabaseUtility;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservationDao {
    public void add(Reservation reservation) {
        EntityManager em = DatabaseUtility.getEntityManager();
        em.persist(reservation);
    }

    public List<Reservation> getAll() {
        EntityManager em = DatabaseUtility.getEntityManager();
        return em.createNamedQuery("getAllReservations").getResultList();
    }

    public Reservation getReservationById(long id) {
        EntityManager em = DatabaseUtility.getEntityManager();
        TypedQuery<Reservation> query = em.createNamedQuery("getRestaurantById", Reservation.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
