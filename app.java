import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class App {

  private HashMap<String, List<Textbook> > listings;

  public App(){
    this.listings = new HashMap<>();
  }
  
  public void addListing(String seller, String code, String title, String author, String ISBN, String price, String condition){
    Textbook book = new Textbook(seller, code, title, author, ISBN, price, condition);
    if listings.contains(seller){
      priorBooks = listings.get(seller);
      currentBooks = priorBooks.add(book);
      listings.put(seller, currentBooks);
    }
    else{
      List<Textbook> books = book;
      listings.put(seller, books);
    }
  }
  public void removeListing(String seller, Textbook book){
    listings.remove(seller, book);
  }
  public static void main(String[] args){
  }



  

}
