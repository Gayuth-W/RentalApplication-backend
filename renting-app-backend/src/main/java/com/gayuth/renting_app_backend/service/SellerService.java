package com.gayuth.renting_app_backend.service;

import com.gayuth.renting_app_backend.model.Seller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService{

    List<Seller> getAllSellers();
}
