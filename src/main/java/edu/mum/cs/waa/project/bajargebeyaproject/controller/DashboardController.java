package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import edu.mum.cs.waa.project.bajargebeyaproject.utils.PdfUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"user","cart"})
public class DashboardController {
    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    NotificationService noticeService;

    @Autowired
    ProductService productService;

    @Autowired
    MailService mailService;

    @Autowired
    CartService cartService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        Map<String, Object> modelMap = model.asMap();
        if(modelMap.containsKey("user")){
            User u = (User)modelMap.get("user");
            model.addAttribute("notifications",u.getNotifications());
            switch(u.getRole().getRole()){
                case "Admin":
                    model.addAttribute("Sellers",userService.getSellers());//.getSellerByApproved(false));
                    model.addAttribute("Reviews", reviewService.getReviewsUnapproved());//.getReviewsByApproved(false));
                    model.addAttribute("Prods",productService.getProducts());//.getProductByAds(false));
                    break;
                case "Buyer":
                    Buyer b = userService.getBuyerByUserId(u.getId());
                    model.addAttribute("Reward", b.getReward());
                    model.addAttribute("OrderEntries",cartService.getPendingOrders());
                    model.addAttribute("ProductOrders",productService.getAllProductOrderByBuyer(b));
                    model.addAttribute("buyer",b);
                case "Seller":
                    model.addAttribute("OrderEntries",cartService.getPendingOrders());
            }

            return "dashboard";
        }
        return "login";
    }

    @GetMapping("/seller/{id}/{bool}")
    public String approveSeller(@PathVariable("id") Long id, @PathVariable("bool") Boolean bool){
        Seller s = userService.getSellerById(id);
        s.setIsApproved(bool);
        s = userService.saveSeller(s);
        return "redirect:/dashboard";
    }

    @GetMapping("/review/{id}/{bool}")
    public String approveReview(@PathVariable("id") Long id, @PathVariable("bool") Boolean bool){
        Review r = reviewService.getReviewById(id);
        r.setIsApproved(bool);
        r = reviewService.save(r);
        return "redirect:/dashboard";
    }

    @GetMapping("/cartEntry/{id}/{status}")
    public String changeOrderStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
        CartEntry ce = cartService.getCartEntryById(id);
        Product p = ce.getProduct();
        switch (status){
            case "delivered":
                //10% of payment reward for each purchase;
                Integer reward = ce.getProductOrder().getBuyer().getReward()+(int)(ce.getSubTotal()/10);
                ce.getProductOrder().getBuyer().setReward(reward);
                noticeService.notify("The "+p.getName()+"has been delivered.","#",p.getSeller());
                noticeService.notify("The "+p.getName()+"has been delivered."+reward+" reward points added. Please send us your feedback","/products/"+p.getId(),ce.getProductOrder().getBuyer().getUser());
                break;
            case "cancelled":
                ProductOrder po = ce.getProductOrder();
                Buyer b = po.getBuyer();
                ReceiptEntry re = new ReceiptEntry();
                re.setProductName(p.getName());
                re.setDiscount(p.getDiscount());
                re.setPrice((-1)*ce.getSubTotal());
                re.setQuantity(ce.getQuantity());
                re.setTax(p.getTax());
                po.getReceipt().getReceiptEntries().add(re);
                userService.saveBuyer(b);
                noticeService.notify("The order for the product ("+p.getName()+") has been cancelled by the buyer.","#",p.getSeller());
                noticeService.notify("The order for the product ("+p.getName()+") has been cancelled.","#",b.getUser());
                noticeService.notifySujiv("Product Order cancelled");
                noticeService.notifyAdmins("Product Order Cancelled :(","#");
        }
        ce.setStatus(status);
        cartService.saveCartEntry(ce);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/printReceipt/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> printReceipt(@PathVariable("id") Long id) {

        ByteArrayInputStream bis = PdfUtil.ReceiptEntries(cartService.getReceipt(id));

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
