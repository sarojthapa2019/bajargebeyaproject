package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.NotificationService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ReviewService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        Map<String, Object> modelMap = model.asMap();
        if(modelMap.containsKey("user")){
            User u = (User)modelMap.get("user");
            switch(u.getRole().getRole()){
                case "Admin":
                    model.addAttribute("Sellers",userService.getSellers());//.getSellerByApproved(false));
                    model.addAttribute("Reviews", reviewService.getReviews());//.getReviewsByApproved(false));
                    model.addAttribute("Products",productService.getProducts());//.getProductByAds(false));
                    break;
                case "Buyer":
                    model.addAttribute("reward", userService.get)

                case "Seller":
            }

            return "dashboard";
        }
        return "login";
    }
}
