package com.example.demoJwt.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String name, email, password;

}
