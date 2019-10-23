package edu.mum.cs.waa.project.bazargebeyaproject.Repository;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
