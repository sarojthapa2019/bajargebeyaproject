package edu.mum.cs.waa.project.bazargebeyaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/product/cart")
    public String cartPage(){
        return "cart";
    }
}
