package com.gayuth.renting_app_backend.service;

import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService{
    @Autowired
    SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Seller register(Seller user) {
        System.out.println(user);
        user.setPassword(encoder.encode(user.getPassword()));
        sellerRepository.save(user);
        return user;
    }
}
