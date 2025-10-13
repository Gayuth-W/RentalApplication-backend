package com.gayuth.renting_app_backend.service;

import com.gayuth.renting_app_backend.model.Seller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SellerService{

    List<Seller> getAllSellers();
    Optional<Seller> getSellerById(Long id);
    void deleteSellerById(Long id);
}
