package com.example.demoJwt.services.jwt;

import com.example.demoJwt.entity.User;
import com.example.demoJwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Optional<User> findFirstByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }

}
