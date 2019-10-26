package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.CartEntry;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Receipt;

import java.util.List;

public interface CartService {
    public Cart saveCart(Cart cart);
    public void removeCart(Cart cart);
    public CartEntry findById(Long id);
    public Cart findCartById(Long id);
    public List<CartEntry> getOrders();

    public CartEntry getCartEntryById(Long id);

    public List<CartEntry> getPendingOrders();

    public void saveCartEntry(CartEntry ce);

    public Receipt getReceipt(Long id);
}
