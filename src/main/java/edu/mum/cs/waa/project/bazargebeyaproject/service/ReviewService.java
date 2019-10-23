package edu.mum.cs.waa.project.bazargebeyaproject.service;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAll();
    Review save(Review review);
    int getAverageRating(Long productId);
    List<Review> findByProductId(Long id);
}
