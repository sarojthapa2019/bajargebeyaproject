package edu.mum.cs.waa.project.bajargebeyaproject.repository;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    List<Role> findAll() ;
}
