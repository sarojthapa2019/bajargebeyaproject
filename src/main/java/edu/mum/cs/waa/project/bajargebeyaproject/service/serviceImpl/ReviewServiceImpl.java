package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Review;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ReviewRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepo reviewRepo;

    @Override
    public Review save(Review rv) {
        return reviewRepo.save(rv);
    }
}
