package com.example.SpringBootExample1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF to allow POST/PUT/DELETE for testing
                .csrf(csrf -> csrf.disable())
                // Allow frames for H2 Console
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth -> auth
                        // Allow access to H2 console and API without login
                        .requestMatchers("/h2-console/**", "/api/products/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}