package edu.mum.cs.waa.project.bajargebeyaproject.repository;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
}
