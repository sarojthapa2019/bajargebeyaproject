package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

<<<<<<< HEAD
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.CartRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
=======
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.CartRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
>>>>>>> c9819c4c6471ab43f2af1d66d3aced6ead1f50d2
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
<<<<<<< HEAD
    @Autowired
    CartRepo cartRepo;

=======
    private CartRepo cartRepo;
>>>>>>> c9819c4c6471ab43f2af1d66d3aced6ead1f50d2
    @Override
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void removeCart(Cart cart) {
<<<<<<< HEAD
        cartRepo.delete(cart);
=======
          cartRepo.delete(cart);
    }

    @Override
    public Optional<Cart> findByBuyer(Buyer buyer) {
        return cartRepo.findByBuyer(buyer);
>>>>>>> c9819c4c6471ab43f2af1d66d3aced6ead1f50d2
    }
}
