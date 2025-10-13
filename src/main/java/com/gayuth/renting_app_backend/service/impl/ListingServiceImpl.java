package com.gayuth.renting_app_backend.service.impl;

import com.gayuth.renting_app_backend.dto.CreateListingDTO;
import com.gayuth.renting_app_backend.dto.DetailListingDTO;
import com.gayuth.renting_app_backend.dto.HomeListingDTO;
import com.gayuth.renting_app_backend.mapper.ListingMapper;
import com.gayuth.renting_app_backend.model.Listing;
import com.gayuth.renting_app_backend.model.ListingImage;
import com.gayuth.renting_app_backend.model.Seller;
import com.gayuth.renting_app_backend.repository.ListingImageRepository;
import com.gayuth.renting_app_backend.repository.ListingRepository;
import com.gayuth.renting_app_backend.repository.SellerRepository;
import com.gayuth.renting_app_backend.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {
    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ListingImageRepository listingImageRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<HomeListingDTO> getAllListingsForHome() {
        return listingRepository.findAll()
                .stream()
                .map(ListingMapper::toHomeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DetailListingDTO getListingDetails(Long id) {
        Listing listing = listingRepository.findByIdWithImages(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        System.out.println("Images fetched from the backend: " + listing.getImages());
        return ListingMapper.toDetailDTO(listing);
    }

    @Override
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    @Override
    public Listing createListingWithImages(CreateListingDTO dto) {
        Seller seller = sellerRepository.findById(dto.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        // Step 1: Create Listing
        Listing listing = new Listing();
        listing.setTitle(dto.getTitle());
        listing.setLocation(dto.getLocation());
        listing.setAddress(dto.getAddress());
        listing.setAbout(dto.getAbout());
        listing.setPrice(dto.getPrice());
        listing.setBedrooms(dto.getBedrooms());
        listing.setBathrooms(dto.getBathrooms());
        listing.setGuests(dto.getGuests());
        listing.setPropertyType(dto.getPropertyType());
        listing.setSeller(seller);

        Listing savedListing = listingRepository.save(listing);

        // Step 2: Save Images
        if (dto.getImageUrls() != null) {
            for (String url : dto.getImageUrls()) {
                ListingImage image = new ListingImage();
                image.setListing(savedListing);
                image.setUrl(url);
                listingImageRepository.save(image);
            }
        }

        return savedListing;
    }

    @Override
    public List<Listing> getListingBySeller(Long id) {
        return listingRepository.findBySellerId(id);
    }

    @Override
    public void deleteListingById(Long id) {
        listingRepository.deleteById(id);
    }
}
