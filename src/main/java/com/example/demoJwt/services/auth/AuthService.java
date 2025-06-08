package com.example.demoJwt.services.auth;

import com.example.demoJwt.dto.SignupRequest;
import com.example.demoJwt.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService extends UserDetailsService {

    UserDto createUser(SignupRequest signupRequest);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
