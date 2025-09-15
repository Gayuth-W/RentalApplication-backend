package com.gayuth.renting_app_backend.repository;

import com.gayuth.renting_app_backend.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {
    VerificationCode findByEmail(String email);
    Optional<VerificationCode> findByEmailAndTokenAndUsedFalse(String email, String token);
}

