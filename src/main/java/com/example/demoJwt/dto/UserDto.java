package com.example.demoJwt.dto;

import com.example.demoJwt.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name,email;
    private UserRole userRole;
}
