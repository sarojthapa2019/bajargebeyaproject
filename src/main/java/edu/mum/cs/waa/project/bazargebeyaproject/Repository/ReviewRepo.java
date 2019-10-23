package edu.mum.cs.waa.project.bazargebeyaproject.Repository;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

    @Query(value = "select avg (r.rating) from Review r where r.product.id = ?1")
    int getAverageRating(Long productId);

    @Query(value = "select r from Review  r where r.product.id = ?1")
    List<Review> findByProductId(Long id);
}
