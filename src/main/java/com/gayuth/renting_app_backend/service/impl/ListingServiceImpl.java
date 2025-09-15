package com.gayuth.renting_app_backend.service.impl;

import com.gayuth.renting_app_backend.model.Listing;
import com.gayuth.renting_app_backend.repository.ListingRepository;
import com.gayuth.renting_app_backend.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {
    @Autowired
    private ListingRepository listingRepository;

    public List<Listing> getAllListings(){
        return listingRepository.findAll();
    }

    @Override
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }
}
