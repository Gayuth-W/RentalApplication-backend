package com.gayuth.renting_app_backend.dto;

import lombok.Data;

@Data
public class VerifyDto {
    private String email;
    private String verificationCode;
}
