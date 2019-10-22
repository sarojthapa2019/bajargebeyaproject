package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Review;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ReviewRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepo reviewRepo;

    @Override
    public Review save(Review rv) {
        return reviewRepo.save(rv);
    }

    @Override
    public List<Review> getReviewsByApproved(boolean b) {
        return (List)reviewRepo.findByApproved(b);
    }

    @Override
    public List<Review> getReviews() {
        return (List)reviewRepo.findAll();
    }
}
