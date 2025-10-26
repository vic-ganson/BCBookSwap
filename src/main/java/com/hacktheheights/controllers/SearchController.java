package com.hacktheheights.controllers;
import com.hacktheheights.models.Account;
import com.hacktheheights.models.Search;
import com.hacktheheights.models.Textbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SearchController {

    private final Search searchBar = new Search(new HashMap<>()); 

    //Login Page
    @GetMapping("/")
    public String home() {
        return "login"; 
    }
    //Listings Page
    @GetMapping("/index")
    public String listings() {
        return "index"; 
    }
    //Create Account Page
    @GetMapping("/createAccount")
    public String createAccount() {
        return "createAccount"; 
    }
    //My Profile Page
    @GetMapping("/account")
    public String account() {
        return "account"; 
    }
    //Sell Book Page
    @GetMapping("/sell")
    public String sell() {
        return "sell"; 
    }
    //edit profile Page
    @GetMapping("/edit_profile")
    public String editProfile() {
        return "edit_profile"; 
    }
    //buy Page
    @GetMapping("/buy")
    public String buy() {
        return "buy"; 
    }
    
    @GetMapping("/search")
    public String search(@RequestParam(name="q", required=false) String query, Model model) {
        HashMap<Account, List<Textbook>> results = searchBar.searchResults(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search";
    }
}
