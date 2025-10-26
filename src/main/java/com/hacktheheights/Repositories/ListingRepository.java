package com.hacktheheights.repositories;

import com.hacktheheights.models.Account;
import com.hacktheheights.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {

    // get all listings by seller
    List<Listing> findBySeller(Account seller);

    // Search listings
    List<Listing> findByTitleContainingIgnoreCaseOrCourseCodeContainingIgnoreCaseOrAuthorContainingIgnoreCase(
        String title, String courseCode, String author
    );
}
