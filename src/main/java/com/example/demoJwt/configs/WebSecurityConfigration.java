package com.example.demoJwt.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request->
                request.requestMatchers("/api/auth/**").permitAll()
                );
        return http.build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AutenticationConfiguration config) throws Exception {
//        return config.getAutenticationManager();
//    }
}
