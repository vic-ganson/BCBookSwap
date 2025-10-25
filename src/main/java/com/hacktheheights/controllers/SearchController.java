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

    @GetMapping("/")
    public String home() {
        return "login"; 
    }
    @GetMapping("/index")
    public String listings() {
        return "index"; 
    }

    @GetMapping("/search")
    public String search(@RequestParam(name="q", required=false) String query, Model model) {
        HashMap<Account, List<Textbook>> results = searchBar.searchResults(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search";
    }
}
