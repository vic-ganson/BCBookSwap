package com.hacktheheights.repositories;

import com.hacktheheights.models.Account;
import com.hacktheheights.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {

    // Get all listings for a specific seller
    List<Listing> findBySeller(Account seller);

    // Search listings by title, course code, or author (ignore case)
    List<Listing> findByTitleContainingIgnoreCaseOrCourseCodeContainingIgnoreCaseOrAuthorContainingIgnoreCase(
        String title, String courseCode, String author
    );
}
