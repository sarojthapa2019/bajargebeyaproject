package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.CartEntry;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartEntryService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@SessionAttributes("cart")
@Controller
public class CartController {

    private CartService cartService;
    private CartEntryService cartEntryService;
    private ProductService productService;
    @Autowired
    public CartController(CartService cartService, CartEntryService cartEntryService, ProductService productService){
        this.cartService = cartService;
        this.cartEntryService = cartEntryService;
        this.productService = productService;
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


    //for adding each item into Cart
    @GetMapping("/cart/items/{id}")
    @ResponseBody
    public  HashMap<String, Object>  addEntryToCart(@PathVariable("id") Long productId, Model model){
        Cart cart = null;
        int itemCount = 0;
        if(productService.findById(productId).isPresent()){
            Product product = productService.findById(productId).get();
             cart = (Cart)model.asMap().get("cart");
            cart.addEntry(product);
            if(cart.getBuyer() == null){
                Buyer buyer = (Buyer) model.asMap().get("user");
                cart.setBuyer(buyer);
            }
            itemCount = cart.getTotalItems();
            cartService.saveCart(cart);

        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("count", itemCount);
        return map;
    }

}
