package com.codegym.project.service.review;

import com.codegym.project.model.Review;
import com.codegym.project.service.IGeneralService;

public interface IReviewService extends IGeneralService<Review> {
    Iterable<Review> findReviewByProductId(Long id);
    int countReviewByProductId(Long id);
}
