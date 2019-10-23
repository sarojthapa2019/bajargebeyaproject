package edu.mum.cs.waa.project.bazargebeyaproject.service;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Admin;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Seller;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.User;

import java.util.List;

public interface UserService {

    public User save(User u);

    public List<User> findAll();

    public List<User> findAllByRole(String role);

    public User findById(Long i);

//    public User save(User u);
//
//    public List<User> findAll();
//
//    public List<User> findAllByRole(String role);
//
//    public User findById(Long i);

    public Buyer saveBuyer(Buyer b);

    public Seller saveSeller(Seller s);

    public Admin saveAdmin(Admin a);

    public Buyer getBuyerById(Long id);

    public Seller getSellerById(Long id);

    public List<Seller> getSellerByApproved(Boolean b);

    public Admin getAdminById(Long id);

    public List<Seller> getSellers();

    public Buyer getBuyerByUserId(Long id);
}
