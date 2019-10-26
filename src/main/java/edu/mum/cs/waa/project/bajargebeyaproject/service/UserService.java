package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User save(User u);

    public List<User> findAll();

    public List<User> findAllByRole(String role);

    public User findById(Long i);

    //public Optional<User> findByEmail(String email);

    public Buyer saveBuyer(Buyer b);

    public Seller saveSeller(Seller s);

    public Admin saveAdmin(Admin a);

    public Buyer getBuyerById(Long id);

    public Seller getSellerById(Long id);

    public List<Seller> getSellerByApproved(boolean b);

    public Admin getAdminById(Long id);

    public List<Seller> getSellers();

    public Buyer getBuyerByUserId(Long id);

    public List<Seller> getSellerByApproved(Boolean b);

    Seller findSellerByUserId(Long userId);
    public List<Role> getAllRole();
    public boolean checkRole(Long id, String role);

    public User findByEmail(String uid);
}
