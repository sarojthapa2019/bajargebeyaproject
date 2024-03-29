package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Review;

import java.util.List;

public interface ReviewService {
    public Review save(Review rv);

    public List<Review> getReviewsByApproved(boolean b);

    public List<Review> getReviews();

    public List<Review> getReviewsUnapproved();

    public Review getReviewById(Long id);
}
