package com.hacktheheights.models;

import jakarta.persistence.*;

@Entity
@Table(name = "listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private String title;
    private String author;
    private String ISBN;
    private double price;
    private String condition;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Account seller;

    public Listing() {}

    public Listing(Account seller, String courseCode, String title, String author, String ISBN, double price, String condition) {
        this.seller = seller;
        this.courseCode = courseCode;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
        this.condition = condition;
    }

    // getters and setters
    public Long getId() { return id; }
    public Account getSeller() { return seller; }
    public void setSeller(Account seller) { this.seller = seller; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
}
