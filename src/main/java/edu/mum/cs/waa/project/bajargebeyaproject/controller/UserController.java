package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@SessionAttributes({"user","cart"})
//@RequestMapping("/user")
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
    @RequestMapping("/check")
    public String check(@RequestParam("uid") String uid){
        Long id = userService.findByEmail(uid).getId();
        return "redirect:/"+id;
    }

    @GetMapping("/{id}")
    public String indexUser(@PathVariable("id") Long id, Model model
//            , RedirectAttributes ra){
    ){
//        model = mockLogin(model, id);
        System.out.println("User id is: "+id);
        if(userService.checkRole(id,"Buyer"))
//            ra.addAttribute("cart", userService.getBuyerByUserId(id).getCart());
            model.addAttribute("cart", userService.getBuyerByUserId(id).getCart());
        model.addAttribute("user", userService.findById(id));
//        ra.addAttribute("user", userService.findById(id));
        return "index";
    }

    private Model mockLogin(Model model, Long id) {
        model.addAttribute("user", userService.findById(id));
        if(userService.checkRole(id,"Buyer"))
            model.addAttribute("cart", userService.getBuyerByUserId(id).getCart());
        return model;
    }

    @GetMapping("/logout")
    public String logout(SessionStatus ss){
        ss.setComplete();
        return "login";
    }

}
