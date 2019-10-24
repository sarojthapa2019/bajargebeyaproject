package edu.mum.cs.waa.project.bajargebeyaproject.repository;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Review;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
    @Query(value="SELECT r FROM Review r WHERE r.isApproved = ?1")
    public List<Review> findByApproved(boolean b);
}
