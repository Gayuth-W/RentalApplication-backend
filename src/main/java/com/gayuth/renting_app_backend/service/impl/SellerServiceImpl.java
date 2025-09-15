package com.gayuth.renting_app_backend.service.impl;

import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.repository.SellerRepository;
import com.gayuth.renting_app_backend.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }
}
