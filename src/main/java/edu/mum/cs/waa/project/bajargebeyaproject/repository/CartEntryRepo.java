package edu.mum.cs.waa.project.bajargebeyaproject.repository;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartEntryRepo extends JpaRepository<CartEntry, Long> {
    @Query(value="SELECT ce FROM CartEntry ce WHERE lower(ce.status) = 'order'")
    public List<CartEntry> getAllPending();
}
