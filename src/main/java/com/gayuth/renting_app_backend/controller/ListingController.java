package com.gayuth.renting_app_backend.controller;

import com.gayuth.renting_app_backend.dto.CreateListingDTO;
import com.gayuth.renting_app_backend.dto.DetailListingDTO;
import com.gayuth.renting_app_backend.dto.HomeListingDTO;
import com.gayuth.renting_app_backend.model.Listing;
import com.gayuth.renting_app_backend.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ListingController {
    @Autowired
    private ListingService listingService;

    @GetMapping("/home")
    public List<HomeListingDTO> getHomeListings() {
        return listingService.getAllListingsForHome();
    }

    @GetMapping("/listing/{id}")
    public DetailListingDTO getListingDetails(@PathVariable Long id) {
        return listingService.getListingDetails(id);
    }

    @PostMapping("/post-listing")
    public Listing createListing(@RequestBody CreateListingDTO dto){
        return listingService.createListingWithImages(dto);
    }
}
