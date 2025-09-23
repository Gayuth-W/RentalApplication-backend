package com.gayuth.renting_app_backend.dto;

import com.gayuth.renting_app_backend.domain.PropertyType;
import com.gayuth.renting_app_backend.model.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeListingDTO {

    private Long id;
    private String title;

}
