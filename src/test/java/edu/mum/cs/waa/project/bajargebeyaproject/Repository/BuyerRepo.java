package edu.mum.cs.waa.project.bajargebeyaproject.Repository;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepo extends JpaRepository<Buyer, Long> {
    @Query(value="SELECT b FROM Buyer b WHERE b.user.id = ?1")
    public Buyer findByUserId(Long id);
}