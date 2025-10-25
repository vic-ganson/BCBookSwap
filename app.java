package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class App {

  private HashMap<Account, List<Textbook> > listings;

  public App(){
    this.listings = new HashMap<>();
  }
  
  public void addListing(Account seller, String code, String title, String author, String ISBN, String price, String condition){
    Textbook book = new Textbook(seller, code, title, author, ISBN, price, condition);
    if (listings.contains(seller)){
      priorBooks = listings.get(seller);
      currentBooks = priorBooks.add(book);
      listings.put(seller, currentBooks);
    }
    else{
      List<Textbook> books = book;
      listings.put(seller, books);
    }
  }
  public void removeListing(Account seller, Textbook book){
    priorBooks = listings.get(seller);
    currentBooks = priorBooks.remove(book);
    listings.put(seller, currentBooks);
  }
  public void removeSeller(Account seller){
    listings.remove(seller);
  }

  
  public static void main(String[] args){
    SpringApplication.run(App.class, args);
  }
  
}
