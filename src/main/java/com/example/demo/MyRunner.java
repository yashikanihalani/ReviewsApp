package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import com.example.demo.repository.UserReviewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
@Component
public class MyRunner implements CommandLineRunner{
    @Autowired
    private UserReviewsRepository userReviewRepo;

    @Value("${reviewSite.resourceUrl}")
    String resourceUrl;
    ImportService importService = new ImportService();

    @Override
    public void run(String...args) throws Exception {
        importService.importReviews(resourceUrl,userReviewRepo);

    }

}
