package com.hacktheheights.controllers;

import com.hacktheheights.models.Listings;
import com.hacktheheights.models.Textbook;
import com.hacktheheights.models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/listings")
public class ListingsController {

    private final Listings listings = new Listings();

    @GetMapping("/{sellerId}")
    public String getSellerListings(@PathVariable String sellerId, Model model) {
        Account seller = new Account(sellerId); // create Account object for now
        List<Textbook> books = listings.getListingsBySeller(seller);
        model.addAttribute("books", books);
        return "sellerListings"; // template: sellerListings.html
    }

    @PostMapping("/add")
    public String addListing(@RequestParam String sellerId,
                             @RequestParam String code,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String ISBN,
                             @RequestParam String price,
                             @RequestParam String condition) {

        Account seller = new Account(sellerId);
        listings.addListing(seller, code, title, author, ISBN, price, condition);
        return "redirect:/listings/" + sellerId;
    }

    @PostMapping("/remove")
    public String removeListing(@RequestParam String sellerId,
                                @RequestParam String code) {
        Account seller = new Account(sellerId);
        List<Textbook> sellerBooks = listings.getListingsBySeller(seller);
        for (Textbook t : sellerBooks) {
            if (t.getCode().equals(code)) {
                listings.removeListing(seller, t);
                break;
            }
        }
        return "redirect:/listings/" + sellerId;
    }

    @PostMapping("/removeSeller")
    public String removeSeller(@RequestParam String sellerId) {
        Account seller = new Account(sellerId);
        listings.removeSeller(seller);
        return "redirect:/";
    }
}
