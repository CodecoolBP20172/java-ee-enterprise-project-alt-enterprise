package com.codecool.restauratio.repository;

import com.codecool.restauratio.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
