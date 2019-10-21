package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CartController {



    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }
}
