package edu.mum.cs.waa.project.bazargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bazargebeyaproject.Repository.AdminRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.Repository.BuyerRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.Repository.SellerRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.Repository.UserRepo;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Admin;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Seller;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bazargebeyaproject.service.UserService;
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
        return (List<User>)userRepo.findAll();
    }

    @Override
    public List<User> findAllByRole(String role) {
        return userRepo.findAllByRole(role);
    }

    @Override
    public User findById(Long i) {
        return userRepo.findById(i).get();
    }


//    @Override
//    public User findById(Long i) {
//        if(userRepo.findById(i).isPresent())
//            return userRepo.findById(i).get();
//        else
//            return null;
//    }

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
    public List<Seller> getSellerByApproved(Boolean b) {
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
}
