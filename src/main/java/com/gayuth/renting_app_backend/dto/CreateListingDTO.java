package com.gayuth.renting_app_backend.dto;

import com.gayuth.renting_app_backend.domain.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateListingDTO {
    private String title;
    private String location;
    private String address;
    private String about;
    private Double price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer guests;
    private PropertyType propertyType;
    private Long sellerId;
    private List<String> imageUrls;
}
