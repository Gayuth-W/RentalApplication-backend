package com.gayuth.renting_app_backend.service.impl;

import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.repository.SellerRepository;
import com.gayuth.renting_app_backend.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public void deleteSellerById(Long id) {
        sellerRepository.deleteById(id);
    }
}
