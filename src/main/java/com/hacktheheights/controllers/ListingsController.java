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
    public String getSellerListings(@PathVariable String name, String email, String password, Model model) {
        Account seller = new Account(name, email, password);
        List<Textbook> books = listings.getListingsBySeller(seller);
        model.addAttribute("books", books);
        return "sellerListings"; // template: sellerListings.html
    }

    @PostMapping("/add")
    public String addListing(@RequestParam String name, String email, String password,
                             @RequestParam String code,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String ISBN,
                             @RequestParam double price,
                             @RequestParam String condition) {
        Account seller = new Account(name, email, password);
        listings.addListing(seller, code, title, author, ISBN, price, condition);
        return "redirect:/listings/" + seller.getEmail();
    }

    @PostMapping("/remove")
    public String removeListing(@RequestParam String name, String email, String password,
                                @RequestParam String code) {
        Account seller = new Account(name, email, password);
        List<Textbook> sellerBooks = listings.getListingsBySeller(seller);
        for (Textbook t : sellerBooks) {
            if (t.getCourseCode().equals(code)) {
                listings.removeListing(seller, t);
                break;
            }
        }
        return "redirect:/listings/" + seller.getEmail();
    }

    @PostMapping("/removeSeller")
    public String removeSeller(@RequestParam String name, String email, String password) {
        Account seller = new Account(name, email, password);
        listings.removeSeller(seller);
        return "redirect:/";
    }
}
