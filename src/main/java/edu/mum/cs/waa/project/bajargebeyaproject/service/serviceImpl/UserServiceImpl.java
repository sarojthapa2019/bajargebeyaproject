package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.AdminRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.BuyerRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.SellerRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.UserRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Admin;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Seller;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BuyerRepo buyerRepo;

    @Autowired
    SellerRepo sellerRepo;

    @Autowired
    AdminRepo adminRepo;

    public User save(User u){
        return userRepo.save(u);
    }

    @Override
    public List<User> findAll() {
        return (List)userRepo.findAll();
    }

    @Override
    public List<User> findAllByRole(String role) {
        return userRepo.findAllByRole(role);
    }

    @Override
    public User findById(Long i) {
        if(userRepo.findById(i).isPresent())
            return userRepo.findById(i).get();
        else
            return null;
    }

    @Override
    public Buyer saveBuyer(Buyer b) {
        return buyerRepo.save(b);
    }

    @Override
    public Seller saveSeller(Seller s) {
        return sellerRepo.save(s);
    }

    @Override
    public Admin saveAdmin(Admin a) {
        return adminRepo.save(a);
    }

    @Override
    public Buyer getBuyerById(Long id) {
        return buyerRepo.getOne(id);
    }

    @Override
    public Seller getSellerById(Long id) {
        return sellerRepo.getOne(id);
    }

    @Override
    public List<Seller> getSellerByApproved(boolean b) {
        return sellerRepo.findByApproved(b);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepo.getOne(id);
    }

    @Override
    public List<Seller> getSellers() {
        return (List)sellerRepo.findAll();
    }

    @Override
    public Buyer getBuyerByUserId(Long id) {
        return buyerRepo.findByUserId(id);
    }

    @Override
    public boolean checkRole(Long id, String role) {
        System.out.println("checking role +"+role+":"+userRepo.findById(id).get().getRole().getRole().equals(role));
        return userRepo.findById(id).get().getRole().getRole().equals(role);
    }

    @Override
    public User findByEmail(String uid) {
        return userRepo.findByEmail(uid);
    }
}
