package com.example.demoJwt.controller.auth;

import com.example.demoJwt.dto.*;
import com.example.demoJwt.entity.User;
import com.example.demoJwt.repository.UserRepository;
import com.example.demoJwt.services.auth.AuthService;
import com.example.demoJwt.services.jwt.UserService;
import com.example.demoJwt.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    private ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        try {
            UserDto createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("User creation failed, come again later", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AutenticationRequest request) {
        try {
            // Normalize the input email
            String normalizedEmail = request.getEmail().trim().toLowerCase();

            // Authenticate with normalized email
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(normalizedEmail, request.getPassword())
            );

            // Get the authenticated principal (should match DB exactly)
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Debug logging
            System.out.println("Authenticated email: " + userDetails.getUsername());

            // Generate JWT
            String jwt = jwtUtil.generateToken(userDetails);

            // Find user using the EXACT email from UserDetails
            Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

            if (optionalUser.isEmpty()) {
                System.out.println("DB lookup failed for: " + userDetails.getUsername());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found for email: " + userDetails.getUsername());
            }

            User user = optionalUser.get();
            AutenticationResponse response = new AutenticationResponse();
            response.setJwt(jwt);
            response.setUserId(user.getId());
            response.setUserRole(user.getUserRole().name());

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login error: " + e.getMessage());
        }
    }

}