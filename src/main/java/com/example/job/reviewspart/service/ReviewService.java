package com.example.job.reviewspart.service;

import com.example.job.reviewspart.model.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface ReviewService {
    ResponseEntity<List<Review>> findAll(Long id);
    ResponseEntity<Review> createReview(Long id, Review review);
    ResponseEntity<Review> findById(Long id,Long id1);
    ResponseEntity<Review> updateReview(Review review, Long id, Long companyId);
    ResponseEntity<String> deleteReview(Long id, Long companyId);
}
