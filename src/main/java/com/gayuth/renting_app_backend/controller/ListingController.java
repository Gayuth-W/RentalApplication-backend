package com.gayuth.renting_app_backend.controller;

import com.gayuth.renting_app_backend.model.Listing;
import com.gayuth.renting_app_backend.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ListingController {
    @Autowired
    private ListingService listingService;

    @GetMapping("/get-listings")
    public List<Listing> getAllListings(){
        return listingService.getAllListings();
    }

    @PostMapping("/post-listing")
    public Listing createListing(@RequestBody Listing listing){
        return listingService.createListing(listing);
    }
}
