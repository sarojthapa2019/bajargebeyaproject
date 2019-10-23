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

@SessionAttributes({"user","cart", "itemCount"})
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
        if((Buyer)model.asMap().get("user") != null) {
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
        model.addAttribute("cart", buyer.getCart());
        return "index";
    }

    @GetMapping("/cart")
    public String showCart(Model model){
        //for retrieving buyer(user) from session
//        Buyer buyer = (Buyer) model.asMap().get("user");
//        if(cartService.findByBuyer(buyer).isPresent()){
//            model.addAttribute("cart", cartService.findByBuyer(buyer).get());
//        }
        Buyer buyer = userService.getBuyerById(1L);
        model.addAttribute("user", buyer);
        model.addAttribute("cart", buyer.getCart());
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
             Long cartId = ((Cart)model.asMap().get("cart")).getId();
             cart = cartService.findCartById(cartId);
            cart.addEntry(product);

            itemCount = cart.getTotalItems();
            for(CartEntry c: cart.getCartEntries())
            System.out.println("entry id "+c.getId());
            cart=cartService.saveCart(cart);
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("count", itemCount);
        return map;
    }


    //for changing the quantity of each products in a cart entry
    @PostMapping(value = "/cart/items/quantity")

    public @ResponseBody HashMap<String, Object> updateQuantity(@RequestBody String  data, Model model)  {
        CartEntry cartEntry = null;
        Gson gson = new Gson();
        Map<String, Double> pair = gson.fromJson(data, Map.class);
        System.out.println(pair.get("itemId"));
        Long id = pair.get("itemId").longValue();
        int quantity = pair.get("quantity").intValue();
        Cart cart = (Cart)model.asMap().get("cart");
        Product product = cartService.findById(id).getProduct();
         cartEntry = cart.updateProductQuantity(product,quantity);
        cart = cartService.saveCart(cart);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemTotal", cartEntry.getSubTotal());
        map.put("total", cart.getTotalAmount());
        map.put("itemId", cartEntry.getId());
        map.put("quantity", cartEntry.getQuantity());
        map.put("" +
                "" +
                "", cart.getTotalItems());
        return map;

    }
    //for clearing cart items
    @GetMapping("/cart/clear")
    public String clearCart(Model model){
        if((Cart)model.asMap().get("cart") != null){
            Cart cart= (Cart)model.asMap().get("cart");
            for (CartEntry c: cart.getCartEntries()
            ) {
                c.setCart(null);
            }

            cart = cartService.saveCart(cart);

        }

        return "redirect:/cart";
    }


}
