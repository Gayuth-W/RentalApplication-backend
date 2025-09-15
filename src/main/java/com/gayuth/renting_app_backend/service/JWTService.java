package com.gayuth.renting_app_backend.service;

import com.gayuth.renting_app_backend.model.SellerPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public interface JWTService {

    String generateToken(SellerPrincipal sellerPrincipal);

    String extractUserName(String token);

    boolean validateToken(String token, UserDetails userDetails);

    long getExpirationTime();

    }
