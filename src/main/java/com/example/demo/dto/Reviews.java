package com.example.demo.dto;

import java.time.Instant;

public class Reviews {
    private String id;
    private String author;
    private int rating;
    private String type;
    private String source;
    private String content;
    private Instant reviewDate;
    private String tags;
    private CustomerDTO customer;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Instant getReviewDate() {
        return reviewDate;
    }
    public void setReviewDate(Instant reviewDate) {
        this.reviewDate = reviewDate;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public CustomerDTO getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}
