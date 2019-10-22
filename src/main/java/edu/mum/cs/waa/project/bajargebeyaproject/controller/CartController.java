package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Cart;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.CartEntry;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;
import java.util.Map;

@SessionAttributes({"user","cart"})
@Controller
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, UserService userService){
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    //for session.isComplete()
    @GetMapping("/gone")
    public String clear(SessionStatus status){
        status.setComplete();
        return "redirect:index";

    }
    //for shopping cart we need common model attributes like cart
    @ModelAttribute
    public void commonCartAttributes(Model model){
        if(model.asMap().containsKey("user")) {
            model.addAttribute("cart", ((Buyer) model.asMap().get("user")).getCart());
            model.addAttribute("itemCount", ((Buyer) model.asMap().get("user")).getCart().getTotalItems());
        }
        else{
            model.addAttribute("cart", userService.getBuyerById(1l).getCart());
            model.addAttribute("itemCount",userService.getBuyerById(1l).getCart().getTotalItems());
        }

    }

    @GetMapping("/index")
    public String index(Model model){
        Buyer buyer = userService.getBuyerById(1L);
        model.addAttribute("user", buyer);
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

//            if(cart.getBuyer() == null){
//                Buyer buyer = (Buyer) model.asMap().get("user");
//                cart.setBuyer(buyer);
//            }
            itemCount = cart.getTotalItems();
            for(CartEntry c: cart.getCartEntries())
            System.out.println("++++"+c.getId());
            cart=cartService.saveCart(cart);
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("count", itemCount);
        return map;
    }


    //for changing the quantity of each products in a cart entry
    @PostMapping("/cart/items/quantity")
    @ResponseBody
    public CartEntry updateQuantity(@RequestBody String  data, Model model)  {
        CartEntry cartEntry = null;
        Gson gson = new Gson();
        Map<String, Double> pair = gson.fromJson(data, Map.class);
        System.out.println(pair.get("itemId"));
        Long id = pair.get("itemId").longValue();
        int quantity = pair.get("quantity").intValue();
        Cart cart = (Cart)model.asMap().get("cart");
        cartEntry = cart.updateProductQuantity(productService.findById(id).get(),quantity);
        return cartEntry;

    }
    //for clearing cart items
    @GetMapping("/cart/clear")
    public String clearCart(Model model){
        if((Cart)model.asMap().get("cart") != null){
            Cart cart= (Cart)model.asMap().get("cart");
            cart.getCartEntries().clear();

        }

        return "redirect:/index";
    }

    //goto checkout

    @GetMapping("/cart/checkout")
    public String checkOut(){
        return "checkout";
    }
}
