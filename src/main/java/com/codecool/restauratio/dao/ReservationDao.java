package com.codecool.restauratio.dao;

import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservationDao {
        public List getAll() {
            EntityManager em = DatabaseUtility.getEntityManager();
            return em.createNamedQuery("getAllReservation").getResultList();
        }
        public Reservation getReservationById(Integer orderId) {
            EntityManager em = DatabaseUtility.getEntityManager();
            TypedQuery<Reservation> queryReservationById = em.createNamedQuery(
                    "getReservationById", Reservation.class
            );
            queryReservationById.setParameter("id", orderId);
            return queryReservationById.getSingleResult();
        }
    }
