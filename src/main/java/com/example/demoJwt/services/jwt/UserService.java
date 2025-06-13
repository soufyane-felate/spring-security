package com.example.demoJwt.services.jwt;

import com.example.demoJwt.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Optional<User> findFirstByEmail(String email);


}
