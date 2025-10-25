package models;
import java.util.*;
public class Search{
  private HashMap<Account, List<Textbook> > listedItems;
  
  public Search(HashMap<Account, List<Textbook> > l){
    this.listedItems = l;
  }

  public HashMap<Account, List<Textbook> > searchResults(String query){
    Map<Account, List<Textbook>> results = new HashMap<>();
    for (Account a : listedItems.keySet()) {
      List<Textbook> books = new ArrayList<>();
      for (Textbook t : listedItems.get(a)){
        if (t.getCourseCode().contains(query) || t.getTitle().contains(query) || t.getAuthor().contains(query) || t.getISBN().contains(query)){
          books.add(t);
        }
        results.put(a, books);
      }
    }
    return results;
  }
}
