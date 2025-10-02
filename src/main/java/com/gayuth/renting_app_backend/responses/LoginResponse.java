package com.gayuth.renting_app_backend.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
    private Long userId;

    public LoginResponse(String token, long expiresIn, Long userId) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.userId = userId;
    }
}