package com.gayuth.renting_app_backend.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String phone;
}
