package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.UserReviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserReviewsRepository extends JpaRepository<UserReviews,String>{

}
