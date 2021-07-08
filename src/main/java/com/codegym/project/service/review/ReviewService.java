package com.codegym.project.service.review;

import com.codegym.project.model.Review;
import com.codegym.project.repository.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    private IReviewRepository reviewRepository;

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Iterable<Review> findReviewByProductId(Long id) {
        return reviewRepository.findReviewByProductId(id);
    }

    @Override
    public int countReviewByProductId(Long id) {
        return reviewRepository.countReviewByProductId(id);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void remove(Long id) {
        reviewRepository.deleteById(id);
    }
}
