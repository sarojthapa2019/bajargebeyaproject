package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;

public interface CartService {
    public Cart saveCart(Cart cart);
    public Cart removeCart(Cart cart);

}
