package edu.mum.cs.waa.project.bajargebeyaproject.repository;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepo extends JpaRepository<Picture,Long> {
    @Query(value = "select  p from Picture p where p.product.id=?1")
   List<Picture> findByProductId(Long productId);
}
