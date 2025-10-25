import java.util.HashMap;
import java.util.Map;
public class SearchBar{
  private HashMap<Account, List<Textbook> > listedItems;
  private String search;
  
  public SearchBar(HashMap<Account, List<Textbook> > l, String s){
    this.listedItems = l;
    this.search = s;
  }

  public HashMap<> searchResults(String query){
    Map<String, List<Textbook> > results = new HashMap<>();
    for (Account a : listedItems){
      List<Textbook> books = new List<>();
      for (Textbook t : listedItems.get(a)){
        if (t.getCourseCode().contains(query) || t.getTitle().contains(query) || t.getAuthor().contains(query) || t.getISBN().contains(query)){
          books.add(t);
        }
        results.put(s, books);
      }
    }
    return results;
  }
}
