package com.gayuth.renting_app_backend.mapper;

import com.gayuth.renting_app_backend.dto.DetailListingDTO;
import com.gayuth.renting_app_backend.dto.HomeListingDTO;
import com.gayuth.renting_app_backend.dto.SellerDTO;
import com.gayuth.renting_app_backend.model.Listing;
import com.gayuth.renting_app_backend.model.ListingImage;

import java.util.List;
import java.util.stream.Collectors;

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

        List<String> imageUrls = listing.getImages()
                .stream()
                .map(ListingImage::getUrl)
                .map(ListingMapper::convertDriveLink)
                .collect(Collectors.toList());

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
                sellerDTO,
                imageUrls
        );
    }

    private static String convertDriveLink(String url) {
        if (url == null) return null;

        String fileId = null;
        String prefix = "/file/d/";
        int start = url.indexOf(prefix);
        if (start != -1) {
            start += prefix.length();
            int end = url.indexOf("/", start);
            if (end != -1) {
                fileId = url.substring(start, end);
            } else {
                fileId = url.substring(start);
            }
        }

        if (fileId != null) {
            System.out.println("https://drive.google.com/uc?export=view&id=" + fileId);
            return "https://drive.google.com/uc?export=view&id=" + fileId;
        } else {
            return url;
        }
    }
}
