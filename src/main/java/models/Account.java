public class Account{

  private String name;
  private String email;
  private String password;

  public Account(String n, String e, String p){
    this.name = n;
    this.email = e;
    this.password = p;
  }

// Accessor methods
  public String getName(){
    return name;
  }
  public String getEmail(){
    return email;
  }
  public String getPassword(){
    return password;
  }

// Allows for editing of profile
  public void setName(String n){
    this.name = n;
  }
  public void setPassword(String p){
    this.password = s;
  }
}
