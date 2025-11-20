package com.gayuth.renting_app_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPasswordDTO {
    private String email;
    private String verificationCode;
    private String newPassword;
}
