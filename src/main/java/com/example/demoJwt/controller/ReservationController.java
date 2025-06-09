package com.example.demoJwt.controller;

import com.example.demoJwt.dto.ReservationRequest;
import com.example.demoJwt.entity.Reservation;
import com.example.demoJwt.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = reservationService.createReservation(reservationRequest);
        return ResponseEntity.ok(reservation);
    }
}