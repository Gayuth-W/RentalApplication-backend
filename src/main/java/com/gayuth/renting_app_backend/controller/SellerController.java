package com.gayuth.renting_app_backend.controller;

import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/get-sellers")
    public List<Seller> getAllSellers(){
        return sellerService.getAllSellers();
    }

    @GetMapping("/get-seller/{id}")
    public Optional<Seller> getSellerById(@PathVariable Long id){
        return sellerService.getSellerById(id);
    }

    @DeleteMapping("/delete-seller-{id}")
    public void deleteSellerById(@PathVariable Long id){
        sellerService.deleteSellerById(id);
    }
}
