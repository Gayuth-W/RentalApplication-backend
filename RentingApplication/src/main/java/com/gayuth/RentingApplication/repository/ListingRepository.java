package com.gayuth.RentingApplication.repository;

import com.gayuth.RentingApplication.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
}