package com.hacktheheights.controllers;

import com.hacktheheights.models.Account;
import com.hacktheheights.models.Listing;
import com.hacktheheights.repositories.AccountRepository;
import com.hacktheheights.repositories.ListingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/listings")
public class ListingsController {

    private final AccountRepository accountRepo;
    private final ListingRepository listingRepo;

    public ListingsController(AccountRepository accountRepo, ListingRepository listingRepo) {
        this.accountRepo = accountRepo;
        this.listingRepo = listingRepo;
    }

    @GetMapping("/{sellerId}")
    public String getSellerListings(@PathVariable Long sellerId, Model model) {
        Account seller = accountRepo.findById(sellerId).orElse(null);
        if (seller == null) return "redirect:/";

        List<Listing> books = listingRepo.findBySeller(seller);
        model.addAttribute("books", books);
        return "sellerListings";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Listing> getAllListings() {
        return listingRepo.findAll();
    }

    @PostMapping("/add")
    public String addListing(@RequestParam Long sellerId,
                             @RequestParam String code,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String ISBN,
                             @RequestParam double price,
                             @RequestParam String condition) {

        Account seller = accountRepo.findById(sellerId).orElse(null);
        if (seller == null) return "redirect:/";

        Listing newListing = new Listing(seller, code, title, author, ISBN, price, condition);
        listingRepo.save(newListing);
        return "redirect:/listings/" + seller.getId();
    }

    @PostMapping("/remove")
    public String removeListing(@RequestParam Long listingId) {
        listingRepo.deleteById(listingId);
        return "redirect:/";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Listing> searchListings(@RequestParam String term) {
        return listingRepo.findByTitleContainingIgnoreCaseOrCourseCodeContainingIgnoreCaseOrAuthorContainingIgnoreCase(term, term, term);
    }
}
