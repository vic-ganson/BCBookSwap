import java.util.HashMap;
import java.util.Map;
public class SearchBar{
  private HashMap<String, Textbook> listedItems;
  private String search;
  
  public SearchBar(HashMap<String,Textbook> l, String s){
    this.listedItems = l;
    this.search = s;
  }

  public HashMap<> searchResults(String query){
    Map<String, Textbook> results = new HashMap<>();
    for (String s : listedItems){
      Textbook t = listedItems.get(s);
      if (t.getCourseCode().contains(query) || t.getTitle().contains(query) || t.getAuthor().contains(query) || t.getISBN().contains(query)){
        results.put(s, t);
      }
    }
    return results;
  }
}
