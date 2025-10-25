package models;
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
    this.password = p;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account)) return false;
    Account other = (Account) o;
    return (Objects.equals(this.email, o.email) && Objects.equals(this.password, o.password));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
