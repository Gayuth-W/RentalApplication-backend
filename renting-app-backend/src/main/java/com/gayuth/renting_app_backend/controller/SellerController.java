package com.gayuth.renting_app_backend.controller;

import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/get-sellers")
    public List<Seller> getAllSellers(){
        return sellerService.getAllSellers();
    }

    @PostMapping("/register")
    public Seller register(@RequestBody Seller seller) {
        System.out.println(seller);
        return sellerService.register(seller);

    }

    @PostMapping("/login")
    public String login(@RequestBody Seller seller) {

        return sellerService.verify(seller);
    }
}
