package com.gayuth.renting_app_backend.controller;

import com.gayuth.renting_app_backend.model.Listing;
import com.gayuth.renting_app_backend.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {

    @Autowired
    private ListingService listingService;

    @GetMapping
    public ResponseEntity<List<Listing>> getListings(@RequestParam(required = false) String search) {
        List<Listing> listings;
        if (search != null && !search.trim().isEmpty()) {
            listings = listingService.searchListings(search);
        } else {
            listings = listingService.getAllListings();
        }
        return ResponseEntity.ok(listings);
    }
}
