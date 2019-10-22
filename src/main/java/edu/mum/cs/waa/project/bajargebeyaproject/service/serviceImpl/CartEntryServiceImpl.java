package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.CartEntry;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.CartEntryRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartEntryServiceImpl implements CartEntryService {
    @Autowired
    CartEntryRepo cartEntryRepo;
    @Override
    public CartEntry saveCartEntry(CartEntry cartEntry) {
        return cartEntryRepo.save(cartEntry);
    }

    @Override
    public void removeCartEntry(CartEntry cartEntry) {
        cartEntryRepo.delete(cartEntry);
    }
}
