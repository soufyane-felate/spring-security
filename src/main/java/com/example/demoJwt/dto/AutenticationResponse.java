package com.example.demoJwt.dto;


import lombok.Data;

@Data
public class AutenticationResponse {
    private String jwt;
    private Long userId;
    private String userRole;


}