package com.gayuth.renting_app_backend.repository;

import com.gayuth.renting_app_backend.model.ListingImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingImageRepository extends JpaRepository<ListingImage, Long> {
}
