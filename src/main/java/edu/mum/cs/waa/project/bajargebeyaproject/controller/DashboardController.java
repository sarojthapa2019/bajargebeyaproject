package edu.mum.cs.waa.project.bajargebeyaproject.controller;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Seller;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
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
    public String getDashboard(Model model) {
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

    @GetMapping("/seller/{id}/{bool}")
    public String approveSeller(@PathVariable("id") Long id, @PathVariable("bool") Boolean bool){
        Seller s = userService.getSellerById(id);
        s.setIsApproved(bool);
        s = userService.saveSeller(s);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

        List<User> user = userService.findAll();

        ByteArrayInputStream bis = PdfUtil.CartEntries(null);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}