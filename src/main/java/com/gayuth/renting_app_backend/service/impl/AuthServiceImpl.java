package com.gayuth.renting_app_backend.service.impl;

import com.gayuth.renting_app_backend.dto.LoginDto;
import com.gayuth.renting_app_backend.dto.RegisterDto;
import com.gayuth.renting_app_backend.dto.VerifyDto;
import com.gayuth.renting_app_backend.exception.InvalidPasswordException;
import com.gayuth.renting_app_backend.exception.UnverifiedAccountException;
import com.gayuth.renting_app_backend.exception.UserNotFoundException;
import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.model.SellerPrincipal;
import com.gayuth.renting_app_backend.model.VerificationCode;
import com.gayuth.renting_app_backend.repository.SellerRepository;
import com.gayuth.renting_app_backend.repository.VerificationCodeRepository;
import com.gayuth.renting_app_backend.service.AuthService;
import com.gayuth.renting_app_backend.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final VerificationCodeRepository verificationCodeRepository;


    public Seller signup(RegisterDto input) {
        Seller seller = new Seller();
        seller.setFname(input.getFname());
        seller.setLname(input.getLname());
        seller.setEmail(input.getEmail());
        seller.setPhone(input.getPhone());
        seller.setPassword(passwordEncoder.encode(input.getPassword()));
        sellerRepository.save(seller);

        VerificationCode code = new VerificationCode();
        code.setSeller(seller);
        code.setEmail(seller.getEmail());
        code.setToken(generateVerificationCode());
        code.setExpiry(LocalDateTime.now().plusMinutes(15));
        code.setUsed(false);
        code.setCreatedAt(LocalDateTime.now());
        verificationCodeRepository.save(code);

        sendVerificationEmail(code);

        return seller;
    }

    public SellerPrincipal authenticate(LoginDto input) {
        Seller seller = sellerRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!isSellerEnabled(seller))
            throw new UnverifiedAccountException("Account not verified. Please verify your account.");

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
            );
            // If we reach here, authentication succeeded
            return (SellerPrincipal) auth.getPrincipal();
        } catch (BadCredentialsException ex) {
            throw new InvalidPasswordException("Password is incorrect");
        }

    }

    private boolean isSellerEnabled(Seller seller) {
        // You need an 'enabled' field in Seller entity
        return seller.isEnabled();
    }

    public void verifyUser(VerifyDto input) {
        Optional<VerificationCode> optionalCode = verificationCodeRepository
                .findByEmailAndTokenAndUsedFalse(input.getEmail(), input.getVerificationCode());

        if (optionalCode.isEmpty())
            throw new RuntimeException("Invalid or expired verification code");

        VerificationCode code = optionalCode.get();

        if (code.getExpiry().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Verification code expired");

        // Mark code as used
        code.setUsed(true);
        verificationCodeRepository.save(code);

        // Enable seller account
        Seller seller = code.getSeller();
        seller.setEnabled(true);
        sellerRepository.save(seller);
    }

    public void resendVerificationCode(String email) {
        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (isSellerEnabled(seller))
            throw new RuntimeException("Account is already verified");

        // Create new VerificationCode
        VerificationCode code = new VerificationCode();
        code.setSeller(seller);
        code.setEmail(seller.getEmail());
        code.setToken(generateVerificationCode());
        code.setExpiry(LocalDateTime.now().plusMinutes(15));
        code.setUsed(false);
        code.setCreatedAt(LocalDateTime.now());
        verificationCodeRepository.save(code);

        // Send email
        sendVerificationEmail(code);
    }

    private void sendVerificationEmail(VerificationCode code) {
        String subject = "Account Verification";
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">"
                + code.getToken() + "</p>"
                + "</div></div></body></html>";

        try {
            emailService.sendVerificationEmail(code.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}
