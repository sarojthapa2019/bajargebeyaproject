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
<<<<<<< HEAD
    public List<Seller> findByApproved(boolean b);

    @Query(value = "select r from Review  r where r.product.id=?1")
    public List<Review> findByProductId(Long productId);

    @Query(value = "select avg (r.rating) from Review  r where r.product.id=?1")
    public int getAverageRating(Long productId);
=======
    public List<Review> findByApproved(boolean b);
>>>>>>> 7f6009ce6a39f7c9b2bac3de84be30eb39878c58
}
