package com.example.demoJwt.services.jwt;

import com.example.demoJwt.entity.User;
import com.example.demoJwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Use case-insensitive search and trim whitespace
        String normalizedEmail = username.trim().toLowerCase();

        User user = userRepository.findFirstByEmail(normalizedEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + normalizedEmail));

        // Return UserDetails with the EXACT email from database
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())  // Critical - use the exact DB value
                .password(user.getPassword())
                .roles(user.getUserRole().name())
                .build();
    }
}