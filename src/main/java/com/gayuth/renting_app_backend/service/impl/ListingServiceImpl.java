package com.gayuth.renting_app_backend.service.impl;

import com.gayuth.renting_app_backend.dto.DetailListingDTO;
import com.gayuth.renting_app_backend.dto.HomeListingDTO;
import com.gayuth.renting_app_backend.mapper.ListingMapper;
import com.gayuth.renting_app_backend.model.Listing;
import com.gayuth.renting_app_backend.repository.ListingRepository;
import com.gayuth.renting_app_backend.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {
    @Autowired
    private ListingRepository listingRepository;

    @Override
    public List<HomeListingDTO> getAllListingsForHome() {
        return listingRepository.findAll()
                .stream()
                .map(ListingMapper::toHomeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DetailListingDTO getListingDetails(Long id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        return ListingMapper.toDetailDTO(listing);
    }

    @Override
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }
}
