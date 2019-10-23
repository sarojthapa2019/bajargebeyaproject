package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@SessionAttributes({"user"})
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

    @GetMapping("/")
    public String index(Model model){
        mockLogin(model, 1l);
        return "index";
    }

    @GetMapping("/{id}")
    public String indexUser(@PathVariable("id") Long id, Model model){
        mockLogin(model, id);
        return "index";
    }

    private void mockLogin(Model model, Long id) {
        model.addAttribute("user", userService.findById(id));
    }

}
