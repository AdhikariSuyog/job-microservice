package com.example.job.reviewspart.service;

import com.example.job.companypart.model.Company;
import com.example.job.companypart.service.CompanyService;
import com.example.job.reviewspart.model.Review;
import com.example.job.reviewspart.repo.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<List<Review>> findAll(Long companyId) {
        return new ResponseEntity<>(reviewRepository.findByCompanyId(companyId), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Review> createReview(Long id, Review review) {
        if (companyService.existsById(id)) {
            Company company = companyService.findById(id).getBody();
            List<Review> reviews = company.getReviews();
            review.setCompany(company);
            reviews.add(review);
            company.setReviews(reviews);
            companyService.updateCompany(company, id);
            return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.CREATED);
        }
        throw new IllegalArgumentException("could not fid company with id " + id);

    }


    @Override
    public ResponseEntity<Review> findById(Long id, Long companyId) {
        var reviews = reviewRepository.findByCompanyId(id);
        for (Review review : reviews) {
            if (Objects.equals(review.getId(), id))
                return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Review(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Review> updateReview(Review review, Long id, Long companyId) {
        if (reviewRepository.existsById(id)) {
            Review review1 = reviewRepository.findById(id).get();
            review1.setDescription(review.getDescription());
            review1.setTitle(review.getTitle());
            review1.setRating(review.getRating());
            return new ResponseEntity<>(reviewRepository.save(review1), HttpStatus.OK);
        }
        return new ResponseEntity<>(review, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteReview(Long id, Long companyId) {
        if ((companyService.findById(companyId) != null) && reviewRepository.existsById(id)) {
            Review review = reviewRepository.findById(id).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(id);
            return new ResponseEntity<>("review with id: " + id + " is deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("review with id: " + id + " is not deleted.", HttpStatus.BAD_REQUEST);
    }
}
