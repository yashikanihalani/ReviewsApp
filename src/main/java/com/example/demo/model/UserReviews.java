package com.example.demo.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name="UserReviews")
public class UserReviews {

    public UserReviews(){}
    
    private UserReviews(UserReviewsBuilder userReviewsBuilder){
        this.id = userReviewsBuilder.id;
        this.author = userReviewsBuilder.author;
        this.rating = userReviewsBuilder.rating;
        this.type = userReviewsBuilder.type;
        this.source = userReviewsBuilder.source;
        this.content = userReviewsBuilder.content;
        this.reviewDate = userReviewsBuilder.reviewDate;
        this.tags = userReviewsBuilder.tags;
        this.companyName = userReviewsBuilder.companyName;
    }
    
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "author")
    private String author;

    @Column(name = "rating")
    private int rating;

    @Column(name="type")
    private String type;

    @Column(name = "source")
    private String source;

    @Column(name = "content",columnDefinition="LONGTEXT")
    private String content;
    
    @Column(name = "reviewDate")
    private Instant reviewDate = Instant.now();

    @Column(name = "tags")
    private String tags;

    @Column(name = "companyName")
    private String companyName;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static class UserReviewsBuilder{
        private String id;
        private String author;
        private int rating;
        private String type;
        private String source;
        private String content;
        private Instant reviewDate;
        private String tags;
        private String companyName;

        public UserReviewsBuilder id(String id){
            this.id=id;
            return this;
        }

        public UserReviewsBuilder author(String author){
            this.author=author;
            return this;
        }

        public UserReviewsBuilder rating(int rating){
            this.rating=rating;
            return this;
        }

        public UserReviewsBuilder type(String type){
            this.type=type;
            return this;
        }

        public UserReviewsBuilder source(String source){
            this.source=source;
            return this;
        }

        public UserReviewsBuilder content(String content){
            this.content=content;
            return this;
        }

        public UserReviewsBuilder reviewDate(Instant reviewDate){
            this.reviewDate=reviewDate;
            return this;
        }

        public UserReviewsBuilder tags(String tags){
            this.tags=tags;
            return this;
        }

        public UserReviewsBuilder companyName(String companyName){
            this.companyName=companyName;
            return this;
        }

        public UserReviews build(){
            UserReviews userReview=new UserReviews(this);
            return userReview;
        } 
    }
}
