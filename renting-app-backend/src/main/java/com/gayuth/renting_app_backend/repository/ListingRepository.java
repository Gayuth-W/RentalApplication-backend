package com.gayuth.renting_app_backend.repository;

import com.gayuth.renting_app_backend.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing,Long> {
}
