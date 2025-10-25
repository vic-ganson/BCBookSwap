package controllers;

import models.Search;
import models.Textbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SearchController {

    private final Search searchBar = new Search(); // your existing search logic

    @GetMapping("/")
    public String home() {
        return "index"; // renders templates/index.html
    }

    @GetMapping("/search")
    public String search(@RequestParam(name="q", required=false) String query, Model model) {
        List<Textbook> results = searchBar.search(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search"; // renders templates/search.html
    }
}
