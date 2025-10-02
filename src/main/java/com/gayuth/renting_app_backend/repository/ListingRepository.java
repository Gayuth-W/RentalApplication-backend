package com.gayuth.renting_app_backend.repository;

import com.gayuth.renting_app_backend.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing,Long> {
    @Query("SELECT l FROM Listing l LEFT JOIN FETCH l.images WHERE l.id = :id")
    Optional<Listing> findByIdWithImages(@Param("id") Long id);
   List<Listing> findBySellerId(Long SellerId);
}
