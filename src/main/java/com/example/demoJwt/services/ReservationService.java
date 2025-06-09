package com.example.demoJwt.services;

import com.example.demoJwt.dto.ReservationRequest;
import com.example.demoJwt.entity.Reservation;
import com.example.demoJwt.repository.ReservationRepository;
import com.example.demoJwt.repository.UserRepository;
import com.example.demoJwt.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public Reservation createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setUser(userRepository.findById(reservationRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        reservation.setEvent(eventRepository.findById(reservationRequest.getEventId()).orElseThrow(() -> new RuntimeException("Event not found")));
        return reservationRepository.save(reservation);
    }
}