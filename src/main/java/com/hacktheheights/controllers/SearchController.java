package com.hacktheheights.controllers;
import com.hacktheheights.models.Account;
import models.Search;
import models.Textbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SearchController {

    private final Search searchBar = new Search(new HashMap<>()); // your existing search logic

    @GetMapping("/")
    public String home() {
        return "index"; // renders templates/index.html
    }

    @GetMapping("/search")
    public String search(@RequestParam(name="q", required=false) String query, Model model) {
        HashMap<Account, List<Textbook>> results = searchBar.searchResults(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search";
    }
}
