import java.util.*;
public class Textbook {
  private Account seller;
  private String courseCode;
  private String title;
  private String author;
  private String ISBN;
  private double price;
  private String condition;

  public Textbook (Account s, String code, String t, String a, String i, double p, String cond){
    this.seller = s;
    this.courseCode = code;
    this.title = t;
    this.author = a;
    this.ISBN = i;
    this.price = p;
    this.condition = cond;
  }
  
// Accessors
  public String getCourseCode() {return courseCode;}
  public String getTitle() {return title;}
  public String getAuthor() {return author;}
  public String getISBN() {return ISBN;}
  public double getPrice() {return price;}
  public String getSeller() {return seller;}
  
// Allows for price changes
  public void setPrice(double p) { this.price = price;}

  public String toString() {
    return String.format("%s - %s by %s (ISBN: %s) $%.2f", courseCode, title, author, isbn, price);
  }
}
