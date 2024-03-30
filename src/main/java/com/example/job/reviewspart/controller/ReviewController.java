package com.example.job.reviewspart.controller;

import com.example.job.reviewspart.model.Review;
import com.example.job.reviewspart.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("review/{companyId}")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Review>> findAllByCompanyId(@PathVariable Long companyId) {
        return reviewService.findAll(companyId);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Review> findById(@PathVariable Long id, @PathVariable Long companyId) {
        return reviewService.findById(id, companyId);
    }

    @PostMapping("/post")
    public ResponseEntity<Review> createReview(@RequestBody Review review, @PathVariable Long companyId) {
        return reviewService.createReview(companyId, review);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updateReview(@RequestBody Review review, @PathVariable Long id, @PathVariable Long companyId) {
        return reviewService.updateReview(review, id, companyId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id, @PathVariable Long companyId) {
        return reviewService.deleteReview(id, companyId);
    }
}
