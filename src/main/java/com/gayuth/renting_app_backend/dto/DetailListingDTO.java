package com.gayuth.renting_app_backend.dto;

import com.gayuth.renting_app_backend.domain.PropertyType;
import com.gayuth.renting_app_backend.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailListingDTO {
    private Long id;
    private String title;
    private String location;
    private String address;

    private String about;
    private Double price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer guests;
    private PropertyType propertyType=PropertyType.SINGLE;
    private SellerDTO seller;
}
