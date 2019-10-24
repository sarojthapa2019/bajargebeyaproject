package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes({"user","cart"})
@RequestMapping("/user")
public class UserController {

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

//    @GetMapping("/")
//    public String index(Model model){
//        model = mockLogin(model, 1l);
//        return "index";
//    }

    @GetMapping("/{id}")
    public String indexUser(@PathVariable("id") Long id, Model model){
        System.out.println("User id is: "+id);
//        model.addAttribute("user", userService.findById(id));
        model = mockLogin(model, id);
        return "index";
    }

    private Model mockLogin(Model model, Long id) {
        model.addAttribute("user", userService.findById(id));
        if(userService.checkRole(id,"Buyer"))
            model.addAttribute("cart", userService.getBuyerByUserId(id).getCart());
        return model;
    }

}
