package edu.mum.cs.waa.project.bazargebeyaproject.Repository;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Long> {
}
