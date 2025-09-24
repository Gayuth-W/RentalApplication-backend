package com.gayuth.renting_app_backend.model;

import com.gayuth.renting_app_backend.domain.PropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="listing")
public class Listing {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="listing_id")
    private Long id;

    private String title;
    private String location;
    private String address;

    private String about;
    private Double price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer guests;
    @Column(name="property_type")
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType=PropertyType.SINGLE;
    @ManyToOne
    @JoinColumn(name="seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ListingImage> images = new ArrayList<>();
}