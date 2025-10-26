package com.hacktheheights.models;
import java.util.*;
public class Listings {
//Currently not using this class, just scared to delete before submission
  private HashMap<Account, List<Textbook>> listings;

  public Listings() {
    this.listings = new HashMap<>();
  }

  public void addListing(Account seller, String code, String title, String author, String ISBN, double price, String condition) {
    Textbook book = new Textbook(seller, code, title, author, ISBN, price, condition);
    List<Textbook> sellerBooks = listings.getOrDefault(seller, new ArrayList<>());
    sellerBooks.add(book);
    listings.put(seller, sellerBooks);
  }

  public void removeListing(Account seller, Textbook book) {
    List<Textbook> sellerBooks = listings.get(seller);
    if (sellerBooks != null) {
      sellerBooks.remove(book);
      listings.put(seller, sellerBooks);
    }
  }

  public void removeSeller(Account seller) {
    listings.remove(seller);
  }

  public List<Textbook> getListingsBySeller(Account seller) {
    return listings.computeIfAbsent(seller, k -> new ArrayList<>());
  }

  public HashMap<Account, List<Textbook>> getAll() {
    return listings;
  }

  public List<Textbook> search(String term) {
    List<Textbook> results = new ArrayList<>();
    String lower = term.toLowerCase();

    for (List<Textbook> books : listings.values()) {
        for (Textbook b : books) {
            if (b.getTitle().toLowerCase().contains(lower) ||
                b.getCourseCode().toLowerCase().contains(lower) ||
                b.getAuthor().toLowerCase().contains(lower)) {
                results.add(b);
            }
        }
    }
    return results;
  }
}
