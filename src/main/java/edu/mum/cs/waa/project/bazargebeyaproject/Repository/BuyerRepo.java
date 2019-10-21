package edu.mum.cs.waa.project.bazargebeyaproject.Repository;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepo extends JpaRepository<Buyer, Long> {
}
