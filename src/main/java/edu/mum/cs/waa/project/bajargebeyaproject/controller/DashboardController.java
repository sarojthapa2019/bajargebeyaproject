package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@Controller
@SessionAttributes({"user"})
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

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        mailService.sendMail2();
        Map<String, Object> modelMap = model.asMap();
        if(modelMap.containsKey("user")){
            User u = (User)modelMap.get("user");
            model.addAttribute("notifications",u.getNotifications());
            switch(u.getRole().getRole()){
                case "Admin":
                    model.addAttribute("Sellers",userService.getSellers());//.getSellerByApproved(false));
                    model.addAttribute("Reviews", reviewService.getReviews());//.getReviewsByApproved(false));
                    model.addAttribute("Prods",productService.getProducts());//.getProductByAds(false));
                    break;
                case "Buyer":
                    Buyer b = userService.getBuyerByUserId(u.getId());
                    model.addAttribute("Reward", b.getReward());
                    model.addAttribute("ProductOrders",b.getProductOrders());
                    model.addAttribute("buyer",b);
                case "Seller":
                    model.addAttribute("OrderEntries");
            }

            return "dashboard";
        }
        return "login";
    }
}
