package com.gayuth.renting_app_backend.mapper;

import com.gayuth.renting_app_backend.dto.DetailListingDTO;
import com.gayuth.renting_app_backend.dto.HomeListingDTO;
import com.gayuth.renting_app_backend.dto.SellerDTO;
import com.gayuth.renting_app_backend.model.Listing;

public class ListingMapper {
    public static HomeListingDTO toHomeDTO(Listing listing){
        return new HomeListingDTO(
                listing.getId(),
                listing.getTitle());
    }

    public static DetailListingDTO toDetailDTO(Listing listing) {

        SellerDTO sellerDTO = new SellerDTO(
                listing.getSeller().getFname(),
                listing.getSeller().getLname(),
                listing.getSeller().getPhone()
        );

        return new DetailListingDTO(
                listing.getId(),
                listing.getTitle(),
                listing.getLocation(),
                listing.getAddress(),
                listing.getAbout(),
                listing.getPrice(),
                listing.getBedrooms(),
                listing.getBathrooms(),
                listing.getGuests(),
                listing.getPropertyType(),
                sellerDTO
        );
    }
}
