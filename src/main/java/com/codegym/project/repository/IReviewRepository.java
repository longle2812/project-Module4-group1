package com.codegym.project.repository;

import com.codegym.project.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.product.id = ?1")
    Iterable<Review> findReviewByProductId(Long id);

    @Query("select count(r) from Review r where r.product.id = ?1")
    int countReviewByProductId(Long id);
}
