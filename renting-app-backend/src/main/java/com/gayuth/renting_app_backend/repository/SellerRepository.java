package com.gayuth.renting_app_backend.repository;

import com.gayuth.renting_app_backend.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {
    Optional<Seller> findByEmail(String username);
}
