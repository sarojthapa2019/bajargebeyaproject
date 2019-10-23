package edu.mum.cs.waa.project.bazargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bazargebeyaproject.Repository.ReviewRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Review;
import edu.mum.cs.waa.project.bazargebeyaproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepo reviewRepo;

    @Override
    public List<Review> getAll() {
        return reviewRepo.findAll();
    }

    @Override
    public Review save(Review review){
        return reviewRepo.save(review);
    }

    @Override
    public int getAverageRating(Long productId) {
        return reviewRepo.getAverageRating(productId);
    }

    @Override
    public List<Review> findByProductId(Long id) {
        return reviewRepo.findByProductId(id);
    }
}
