package com.codecool.restauratio.repository;

import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE guest_id = :userId")
    List<Reservation> findAllByUser(@Param("userId") Integer userId);
}
