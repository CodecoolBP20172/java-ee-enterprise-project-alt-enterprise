package com.codecool.restauratio.services;

import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    public List<Reservation> findAllBy(User user) {
        return reservationRepository.findAllByGuest(user);
    }
}
