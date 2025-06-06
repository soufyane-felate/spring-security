package com.example.demoJwt.dto;


import lombok.Data;

@Data
public class AutenticationRequest {
    private String email,password;
}
