package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.CartEntry;

import java.util.Optional;

public interface CartService {
    public Cart saveCart(Cart cart);
    public void removeCart(Cart cart);
    public CartEntry findById(Long id);
    public Cart findCartById(Long id);
}
