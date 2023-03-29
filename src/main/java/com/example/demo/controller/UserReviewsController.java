package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.UserReviews;
import com.example.demo.repository.UserReviewsRepository;
import java.util.List;
import java.util.Optional;

@RestController
public class UserReviewsController {
    
    @Autowired
    private UserReviewsRepository userReviewRepo;

    @GetMapping("/reviews")
    public List<UserReviews> getUserReviews(){
        return userReviewRepo.findAll();
    }

    @GetMapping("/reviews/{id}")
    public Optional<UserReviews> getUserReviewsById(@PathVariable(value = "id") String id){
            return userReviewRepo.findById(id);
    }

    @DeleteMapping("/reviews/{id}")
    public String deleteUserReviewById(@PathVariable(value = "id")String id){
        Optional<UserReviews> userReviews = userReviewRepo.findById(id);
        if(userReviews.isPresent()){
            userReviewRepo.deleteById(id);
            return "Review deleted successfully";
        }
        return "Review does not exist";
    }
    
}
