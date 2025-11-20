package com.gayuth.renting_app_backend.service;

import com.gayuth.renting_app_backend.dto.ForgetPasswordDTO;
import com.gayuth.renting_app_backend.dto.LoginDto;
import com.gayuth.renting_app_backend.dto.RegisterDto;
import com.gayuth.renting_app_backend.dto.VerifyDto;
import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.model.SellerPrincipal;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    Seller signup(RegisterDto input);
    SellerPrincipal authenticate(LoginDto input);
    void verifyUser(VerifyDto input);
    void resendVerificationCode(String email);
    void verifyOldUser(String email);
    void resetPassword(ForgetPasswordDTO forgetPasswordDTO);
}
