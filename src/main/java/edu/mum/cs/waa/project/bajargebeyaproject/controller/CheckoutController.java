package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import edu.mum.cs.waa.project.bajargebeyaproject.utils.PdfUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@SessionAttributes({"user","cart"})
@Controller
public class CheckoutController {
    private UserService userService;
    private ProductService productService;
    private CartService cartService;
    private PaymentService paypalService;
    private NotificationService noticeService;

    @Autowired
    public CheckoutController(UserService userService, ProductService productService, CartService cartService, PaymentService paypalService, NotificationService noticeService){
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.paypalService = paypalService;
        this.noticeService = noticeService;
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

    @GetMapping("/cart/checkout/order/{reward}")
    public String placeOrder(@PathVariable(value="reward", required = false) Boolean reward, Model model) throws Exception {
        Cart cart = (Cart) model.asMap().get("cart");
        User user = (User) model.asMap().get("user");
        Buyer buyer = userService.getBuyerByUserId(user.getId());
        ProductOrder productOrder = new ProductOrder();

        productOrder.setBuyer(buyer);
        productOrder.setShippingAddress(buyer.getUser().getBillingAddress());
        productOrder.setStatus("pending");
        productOrder.setReceipt(new Receipt());
        productOrder = productService.saveOrder(productOrder);
        Receipt rcp = productOrder.getReceipt();
        for (CartEntry c : cart.getCartEntries()
        ) {
            c.setCart(null);
            c.setStatus("order");
            c.setProductOrder(productOrder);
            ReceiptEntry re = new ReceiptEntry();
            Product p = c.getProduct();
            re.setProductName(p.getName());
            if (!reward) {
                re.setDiscount(p.getDiscount());
                re.setPrice(c.getSubTotal());
                re.setQuantity(c.getQuantity());
                re.setTax(p.getTax());
                rcp.setTotal(rcp.getTotal() + re.getPrice());
            }
            rcp.getReceiptEntries().add(re);
            re.setReceipt(rcp);
        }

        cart = cartService.saveCart(cart);
        model.addAttribute("productOrders", productService.getAllProductOrderByBuyer(buyer));
        if (reward)
            return "redirect:/order/list";
        else {
            String retUrl = "redirect:" + paypalService.makePayment(rcp.getTotal(), "" + productOrder.getId());//order/list";
            if(retUrl.indexOf("order/list")>0){
                if(PdfUtil.saveReceipt(productOrder.getReceipt())){
                    noticeService.sendReceipt("Purchase Receipt","po"+productOrder.getReceipt().getId()+"receipt.pdf",productOrder.getBuyer().getUser());
                }
                else {
                    noticeService.sendReceipt(productOrder.getReceipt().getId(),productOrder.getBuyer().getUser());
                }
            }
            return retUrl;
        }
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
        ProductOrder po = productService.findProductOrderById(id);
        po.setStatus("cancelled");
        Buyer b = po.getBuyer();
        for(CartEntry ce:po.getCartEntries()){
            ce.setStatus("cancelled");
            ReceiptEntry re = new ReceiptEntry();
            re.setProductName(ce.getProduct().getName());
            re.setDiscount(ce.getProduct().getDiscount());
            re.setPrice((-1)*ce.getSubTotal());
            re.setQuantity(ce.getQuantity());
            re.setTax(ce.getProduct().getTax());
            po.getReceipt().getReceiptEntries().add(re);
            noticeService.notify("The order for the products order has been cancelled by the buyer.","#",ce.getProduct().getSeller());
        }
        userService.saveBuyer(b);
        po = productService.saveOrder(productService.findProductOrderById(id));
        noticeService.notify("The order for the products order has been cancelled.","#",b.getUser());
        noticeService.notifySujiv("Product Order cancelled");
        noticeService.notifyAdmins(" "+po.getCartEntries().size()+" Products Order Cancelled :(","#");
        return "redirect:/order/list";
    }

    @GetMapping("/payment/cancel/{id}")
    public String cancelOrderId(@PathVariable("id") Long id){
        return "redirect:/order/cancel/"+id;
    }

}
