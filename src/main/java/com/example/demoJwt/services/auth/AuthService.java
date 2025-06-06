package com.example.demoJwt.services.auth;

import com.example.demoJwt.dto.SignupRequest;
import com.example.demoJwt.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
