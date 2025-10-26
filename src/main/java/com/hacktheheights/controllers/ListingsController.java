package com.hacktheheights.controllers;
import com.hacktheheights.repositories.AccountRepository;
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
    public String getSellerListings(@PathVariable Long sellerId, Model model) {
        List<Account> allAccounts = accountRepo.findAll();
        Account seller = new Account();
        for (Account acc : allAccounts){
            if (acc.getId().equals(sellerId)){
                seller = acc;
                break;
            }
            Long identification = acc.getId();
            String email = acc.getEmail();
            System.out.println("ID: " + identification + ", Email: " + email);
        }
        List<Textbook> books = listings.getListingsBySeller(seller);
        model.addAttribute("books", books);
        return "sellerListings"; // template: sellerListings.html
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Textbook> getAllListings() {
        List<Textbook> all = new ArrayList<>();
        Map<Account, List<Textbook>> data = listings.getAll();
        for (List<Textbook> sellerBooks : data.values()) {
            all.addAll(sellerBooks);
        }
        return all;
    }

    @PostMapping("/add")
    public String addListing(@RequestParam Long sellerId,
                             @RequestParam String code,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String ISBN,
                             @RequestParam double price,
                             @RequestParam String condition) {
        List<Account> allAccounts = accountRepo.findAll();
        Account seller = new Account();
        for (Account acc : allAccounts){
            if (acc.getId().equals(sellerId)){
                seller = acc;
                break;
            }
        }
        listings.addListing(seller, code, title, author, ISBN, price, condition);
        return "redirect:/listings/" + seller.getId();
    }

    @PostMapping("/remove")
    public String removeListing(@RequestParam Long sellerId, @RequestParam String code) {
        List<Account> allAccounts = accountRepo.findAll();
        Account seller = new Account();
        for (Account acc : allAccounts){
            if (acc.getId().equals(sellerId)){
                seller = acc;
                break;
            }
        }
        List<Textbook> sellerBooks = listings.getListingsBySeller(seller);
        for (Textbook t : sellerBooks) {
            if (t.getCourseCode().equals(code)) {
                listings.removeListing(seller, t);
                break;
            }
        }
        return "redirect:/listings/" + seller.getId();
    }

    @PostMapping("/removeSeller")
    public String removeSeller(@RequestParam Long sellerId) {
        List<Account> allAccounts = accountRepo.findAll();
        Account seller = new Account();
        for (Account acc : allAccounts){
            if (acc.getId().equals(sellerId)){
                seller = acc;
                break;
            }
        }
        listings.removeSeller(seller);
        return "redirect:/";
    }

    @GetMapping("/listings/search")
    @ResponseBody
    public List<Textbook> searchListings(@RequestParam String term) {
        return listings.search(term);
    }
}
