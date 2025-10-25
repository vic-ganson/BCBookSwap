public class Textbook {
  private String courseCode;
  private String title;
  private String author;
  private String ISBN;
  private double price;
  private String seller;

  public Textbook (c, t, a, i, p, s){
    this.courseCode = c;
    this.title = t;
    this.author = a;
    this.ISBN = i;
    this.price = p;
    this.seller = s;
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
