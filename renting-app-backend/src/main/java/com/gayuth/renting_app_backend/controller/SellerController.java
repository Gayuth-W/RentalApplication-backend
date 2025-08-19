package com.gayuth.renting_app_backend.controller;

import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/get-sellers")
    public List<Seller> getAllSellers(){
        return sellerService.getAllSellers();
    }
}
