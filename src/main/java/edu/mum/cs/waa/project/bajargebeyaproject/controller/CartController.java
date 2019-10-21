package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String cart(){
        return "cart";
    }
}
