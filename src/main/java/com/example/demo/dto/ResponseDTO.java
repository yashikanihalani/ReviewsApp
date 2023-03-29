package com.example.demo.dto;

import java.util.List;

public class ResponseDTO {
    private Paging paging;
    private List<Reviews> reviews;
    public Paging getPaging() {
        return paging;
    }
    public void setPaging(Paging paging) {
        this.paging = paging;
    }
    public List<Reviews> getReviews() {
        return reviews;
    }
    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
    
}
