package com.example.demoJwt.dto;


import com.example.demoJwt.enums.UserRole;
import lombok.Data;

@Data
public class AutenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;

}
