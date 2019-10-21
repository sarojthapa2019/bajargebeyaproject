package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;

import java.util.Optional;

public interface CartService {
    public Cart saveCart(Cart cart);
    public void removeCart(Cart cart);
<<<<<<< HEAD

=======
    public Optional<Cart> findByBuyer(Buyer buyer);
>>>>>>> c9819c4c6471ab43f2af1d66d3aced6ead1f50d2
}
