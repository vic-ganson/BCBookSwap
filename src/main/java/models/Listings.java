package models;
import java.util.*;

public class Listings {

  private HashMap<Account, List<Textbook> > listings;

  public Listings(){
    this.listings = new HashMap<>();
  }
  
  public void addListing(Account seller, String code, String title, String author, String ISBN, String price, String condition){
    Textbook book = new Textbook(seller, code, title, author, ISBN, price, condition);
    List<Textbook> sellerBooks = listings.getOrDefault(seller, new ArrayList<>());
    sellerBooks.add(book);
    listings.put(seller, sellerBooks);
  }
  public void removeListing(Account seller, Textbook book){
    List<Textbook> sellerBooks = listings.get(seller);
    if (sellerBooks != null) {
      sellerBooks.remove(book);
      listings.put(seller, sellerBooks);
    }
  }
  public void removeSeller(Account seller){
    listings.remove(seller);
  }
  public List<Textbook> getListingsBySeller(Account seller) {
    return listings.getOrDefault(seller, new ArrayList<>());
  }
  public HashMap<Account, List<Textbook>> getAllListings() {
    return listings;
  }
}
