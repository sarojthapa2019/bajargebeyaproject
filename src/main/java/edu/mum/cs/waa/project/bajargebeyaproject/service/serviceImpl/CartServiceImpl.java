package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.CartEntry;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.CartEntryRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.CartRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;


import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartEntryRepo cartEntryRepo;
    @Override
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void removeCart(Cart cart) {
         cartRepo.delete(cart);
    }

    @Override
    public CartEntry findById(Long id) {
        return cartEntryRepo.getOne(id);
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepo.getOne(id);
    }

//    @Override
//    public Optional<Cart> findByBuyer(Buyer buyer) {
//        return cartRepo.findByBuyer(buyer);
//
//    }

}
