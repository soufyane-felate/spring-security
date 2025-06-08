package com.example.demoJwt.services.auth;

import com.example.demoJwt.dto.SignupRequest;
import com.example.demoJwt.dto.UserDto;
import com.example.demoJwt.entity.User;
import com.example.demoJwt.enums.UserRole;
import com.example.demoJwt.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAcount(){
        Optional<User>adminAcount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAcount.isEmpty()){
          User user = new User();
          user.setEmail("admin@example.com");
          user.setName("admin");
          user.setPassword(new BCryptPasswordEncoder().encode("admin"));

          user.setUserRole(UserRole.ADMIN);
          userRepository.save(user);
            System.out.println("Admin account created");
        }else {
            System.out.println("Admin account already exists");
        }

    }

    public UserDto createUser(SignupRequest signupRequest){
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use"+signupRequest.getEmail());

        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().name());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(authority)
        );
    }

}
