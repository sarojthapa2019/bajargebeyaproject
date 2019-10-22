package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.CartEntry;

public interface CartEntryService {
    public CartEntry saveCartEntry(CartEntry cartEntry);
    public void removeCartEntry(CartEntry cartEntry);
}
