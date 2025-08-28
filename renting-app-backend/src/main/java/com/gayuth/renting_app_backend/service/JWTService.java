package com.gayuth.renting_app_backend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public interface JWTService {

    String generateToken(String username);

    String extractUserName(String token);

    boolean validateToken(String token, UserDetails userDetails);

    }
