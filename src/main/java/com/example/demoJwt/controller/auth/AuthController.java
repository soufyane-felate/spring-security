package com.example.demoJwt.controller.auth;

import com.example.demoJwt.dto.*;
import com.example.demoJwt.entity.User;
import com.example.demoJwt.repository.UserRepository;
import com.example.demoJwt.services.auth.AuthService;
import com.example.demoJwt.services.jwt.UserService;
import com.example.demoJwt.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/signup")
    private ResponseEntity<?>signupUser(@RequestBody SignupRequest signupRequest){
        try {
            UserDto createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }catch (EntityExistsException e){
            return new ResponseEntity<>("User already exists" , HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("User creation failed,come again later" , HttpStatus.BAD_REQUEST);
        }
    }


    /// ///////////////////
    ///
    ///
//   @PostMapping("/login")
//    public AutenticationResponse createAutenticationtoken(@RequestBody AutenticationRequest autenticationRequest){
//     try{
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(autenticationRequest
//                        .getEmail(),
//                        autenticationRequest
//                                .getPassword()));
//     }catch (BadCredentialsException e){
//         throw new BadCredentialsException("Incorrect userName or password");
//     }
//     final UserDetails userDetails=userService.userDetailsService().loadUserByUsername(autenticationRequest.getEmail());
//        Optional<User>optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
//        final String jwt=jwtUtil.generateToken(userDetails);
//        AutenticationResponse autenticationResponse=new AutenticationResponse();
//        if (optionalUser.isPresent()){
//            autenticationResponse.setJwt(jwt);
//            autenticationResponse.setUserRole(optionalUser.get().getUserRole());
//            autenticationResponse.setUserId(optionalUser.get().getId());
//        }
//        return autenticationResponse;
//    }

//    @PostMapping("/sign-in")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest loginRequest) {
//        // Authenticate user
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        com.securityTest.securityTest.security.services.UserDetailsImpl userDetails = (com.securityTest.securityTest.security.services.UserDetailsImpl) authentication.getPrincipal();
//
//        String jwt = jwtUtils.generateJwtToken(userDetails);
//
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        RefreshToken refreshToken = refreshTokenService.createOrUpdateRefreshToken(userDetails.getId());
//
//        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
//
//        return ResponseEntity.ok(new com.ensias.ensiasnote.payload.response.JwtResponse(
//                jwt,
//                refreshToken.getToken(),
//                userDetails.getId(),
//                user.get().getNom(),
//                user.get().getPrenom(),
//                userDetails.getEmail(),
//                roles
//        ));
//    }

}
