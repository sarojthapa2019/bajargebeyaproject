package edu.mum.cs.waa.project.bajargebeyaproject.repository;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Seller;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value="SELECT u FROM User u WHERE u.role.role=?1")
    public List<User> findAllByRole(String role);

    public Optional<User> findByEmail(String email);


}
