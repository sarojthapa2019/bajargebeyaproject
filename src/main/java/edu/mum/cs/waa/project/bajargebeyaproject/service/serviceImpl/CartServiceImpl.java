package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.CartRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepo cartRepo;

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void removeCart(Cart cart) {
        cartRepo.delete(cart);
    }

    @Override
    public Optional<Cart> findByBuyer(Buyer buyer) {
        return cartRepo.findByBuyer(buyer);
    }
}
