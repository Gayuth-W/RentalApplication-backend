package com.gayuth.renting_app_backend.service;

import com.gayuth.renting_app_backend.dto.DetailListingDTO;
import com.gayuth.renting_app_backend.dto.HomeListingDTO;
import com.gayuth.renting_app_backend.model.Listing;
import com.gayuth.renting_app_backend.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListingService {
    List<HomeListingDTO> getAllListingsForHome();
    DetailListingDTO getListingDetails(Long id);
    Listing createListing(Listing listing);
}
