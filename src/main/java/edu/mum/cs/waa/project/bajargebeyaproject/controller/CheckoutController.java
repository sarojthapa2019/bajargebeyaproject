package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"user","cart"})
@Controller
public class CheckoutController {
    private UserService userService;
    private ProductService productService;
    private CartService cartService;

    @Autowired
    public CheckoutController(UserService userService, ProductService productService, CartService cartService){
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

//     //for shopping cart we need common model attributes like cart
//    // here we are providing hardcoded user id
//    @ModelAttribute
//    public void commonCartAttributes(Model model){
//        if((Buyer)model.asMap().get("user")!=null) {
//            model.addAttribute("cart", ((Buyer) model.asMap().get("user")).getCart());
//            model.addAttribute("itemCount", ((Buyer) model.asMap().get("user")).getCart().getTotalItems());
//        }
//        else{
//            model.addAttribute("cart", userService.getBuyerById(1l).getCart());
//            model.addAttribute("itemCount",userService.getBuyerById(1l).getCart().getTotalItems());
//        }
//
//    }
    //goto checkout
    @GetMapping("/cart/checkout")
    public String checkOut(Model model){
//        User user = (User)model.asMap().get("user");
//        Buyer buyer = userService.getBuyerByUserId(user.getId());
//        model.addAttribute("cart", buyer.getCart());
        return "checkout";
    }

    @GetMapping("/cart/checkout/order")
    public String placeOrder(Model model){
        Cart cart = (Cart)model.asMap().get("cart");
        User user = (User)model.asMap().get("user");
        Buyer buyer = userService.getBuyerByUserId(user.getId());
        ProductOrder productOrder = new ProductOrder();

        productOrder.setBuyer(buyer);
        productOrder.setShippingAddress(buyer.getUser().getBillingAddress());
        productOrder.setStatus("pending");
        productOrder.setReceipt(new Receipt());
        productOrder = productService.saveOrder(productOrder);
        Receipt rcp = productOrder.getReceipt();
        for (CartEntry c: cart.getCartEntries()
             ) {
            c.setCart(null);
            c.setStatus("order");
            c.setProductOrder(productOrder);
            ReceiptEntry re = new ReceiptEntry();
            Product p = c.getProduct();
            re.setProductName(p.getName());
            re.setDiscount(p.getDiscount());
            re.setPrice(c.getSubTotal());
            re.setQuantity(c.getQuantity());
            re.setTax(p.getTax());
            rcp.getReceiptEntries().add(re);
            re.setReceipt(rcp);
        }

        cart = cartService.saveCart(cart);

        model.addAttribute("productOrders", productService.getAllProductOrderByBuyer(buyer));
        return "redirect:/order/list";
    }
    @GetMapping("/order/list")
    public String orderHistory(Model model){
        User user = (User)model.asMap().get("user");
        Buyer buyer = userService.getBuyerByUserId(user.getId());
        model.addAttribute("productOrders", productService.getAllProductOrderByBuyer(buyer));
        return "orderSuccess";
    }

    @GetMapping("/order/cancel/{id}")
    public String cancelOrder(@PathVariable Long id){
        productService.findProductOrderById(id).setStatus("cancelled");
        ProductOrder p = productService.saveOrder(productService.findProductOrderById(id));
        return "redirect:/order/list";
    }

}
