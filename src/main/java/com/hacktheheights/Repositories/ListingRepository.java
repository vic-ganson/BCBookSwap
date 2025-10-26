package com.hacktheheights.repositories;

import com.hacktheheights.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findBySellerId(Long sellerId);
}
