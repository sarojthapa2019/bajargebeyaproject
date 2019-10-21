package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CartController {

    private CartService cartService;
    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    //for shopping cart we need common model attributes like cart
    @ModelAttribute
    public void commonCartAttributes(Model model){
        model.addAttribute("cart", new Cart());
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/cart")
    public String showCart(Model model){
        //for retrieving buyer(user) from session
//        Buyer buyer = (Buyer) model.asMap().get("user");
//        if(cartService.findByBuyer(buyer).isPresent()){
//            model.addAttribute("cart", cartService.findByBuyer(buyer).get());
//        }
        return "cart";
    }


}
