package com.gayuth.renting_app_backend.controller;

import com.gayuth.renting_app_backend.dto.LoginDto;
import com.gayuth.renting_app_backend.dto.RegisterDto;
import com.gayuth.renting_app_backend.dto.VerifyDto;
import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.model.SellerPrincipal;
import com.gayuth.renting_app_backend.service.AuthService;
import com.gayuth.renting_app_backend.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    private final JWTService jwtService;
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<Seller> register(@RequestBody RegisterDto registerDto) {
        // Signup returns the Seller entity
        Seller registeredSeller = authService.signup(registerDto);
        return ResponseEntity.ok(registeredSeller);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginUserDto) {
        // Authenticate returns a SellerPrincipal
        SellerPrincipal sellerPrincipal = authService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(sellerPrincipal);
        long expirationTime = jwtService.getExpirationTime();
        Long sellerId = sellerPrincipal.getId();

        // Return token and expiration
        return ResponseEntity.ok(new LoginResponse(jwtToken, expirationTime, sellerId));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyDto verifyUserDto) {
        try {
            authService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            authService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Inner class for login response
    public record LoginResponse(String token, long expiresAt, Long sellerId) {}
}