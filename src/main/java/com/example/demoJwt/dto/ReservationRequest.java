package com.example.demoJwt.dto;

import lombok.Data;

@Data
public class ReservationRequest {
    private Long userId;
    private Long eventId;
}