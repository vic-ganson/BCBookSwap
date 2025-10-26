package com.hacktheheights.controllers;

import com.hacktheheights.models.Account;
import com.hacktheheights.models.Listing;
import com.hacktheheights.repositories.AccountRepository;
import com.hacktheheights.repositories.ListingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
    @ResponseBody
    public String removeListing(@RequestParam Long sellerId, @RequestParam String code) {
        List<Account> allAccounts = accountRepo.findAll();
        Account seller = null;
        for (Account acc : allAccounts) {
            if (acc.getId().equals(sellerId)) {
                seller = acc;
                break;
            }
        }
    
        if (seller == null) {
            System.out.println("seller not found : " + sellerId);
            return "Seller not found";
        }
    
        List<Textbook> sellerBooks = listings.getListingsBySeller(seller);
        if (sellerBooks == null || sellerBooks.isEmpty()) {
            System.out.println("no listings found");
            return "No listings found for this seller";
        }
    
        boolean removed = false;
        Iterator<Textbook> iterator = sellerBooks.iterator();
        while (iterator.hasNext()) {
            Textbook t = iterator.next();
            if (t.getCourseCode().equalsIgnoreCase(code)) {
                iterator.remove();
                removed = true;
                System.out.println("removed: " + t.getTitle() + " (" + t.getCourseCode() + ")");
                break;
            }
        }
    
        if (!removed) {
            return "Listing not found";
        }
    
        listings.getAll().put(seller, sellerBooks);
    
        return "Listing successfully removed";
    }


    @GetMapping("/search")
    @ResponseBody
    public List<Map<String, Object>> searchListings(@RequestParam String term) {
        List<Listing> results = listingRepo
            .findByTitleContainingIgnoreCaseOrCourseCodeContainingIgnoreCaseOrAuthorContainingIgnoreCase(term, term, term);

        List<Map<String, Object>> formatted = new ArrayList<>();

        for (Listing listing : results) {
            Map<String, Object> data = new HashMap<>();
            data.put("title", listing.getTitle());
            data.put("author", listing.getAuthor());
            data.put("courseCode", listing.getCourseCode());
            data.put("price", listing.getPrice());
            data.put("condition", listing.getCondition());
    
            // Include seller email so frontend can show it
            if (listing.getSeller() != null) {
                data.put("sellerEmail", listing.getSeller().getEmail());
            } else {
                data.put("sellerEmail", "Unavailable");
            }
    
            formatted.add(data);
        }
    
        return formatted;
    }

}
