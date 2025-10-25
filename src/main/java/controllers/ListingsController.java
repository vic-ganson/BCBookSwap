package controllers;

import models.Listings;
import models.Textbook;
import models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/listings")
public class ListingsController {

    private final Listings listings = new Listings();

    @GetMapping("/{sellerId}")
    public String getSellerListings(@PathVariable Account seller, Model model) {
        List<Textbook> books = listings.getListingsBySeller(seller);
        model.addAttribute("books", books);
        return "sellerListings"; // template: sellerListings.html
    }

    @PostMapping("/add")
    public String addListing(@RequestParam Account seller,
                             @RequestParam String code,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String ISBN,
                             @RequestParam double price,
                             @RequestParam String condition) {
        listings.addListing(seller, code, title, author, ISBN, price, condition);
        return "redirect:/listings/" + seller.getEmail();
    }

    @PostMapping("/remove")
    public String removeListing(@RequestParam Account seller,
                                @RequestParam String code) {
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
    public String removeSeller(@RequestParam Account seller) {
        listings.removeSeller(seller);
        return "redirect:/";
    }
}
