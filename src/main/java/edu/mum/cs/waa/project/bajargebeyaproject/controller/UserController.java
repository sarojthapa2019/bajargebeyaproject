package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Buyer;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Category;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.ProductInfo;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Seller;
import edu.mum.cs.waa.project.bajargebeyaproject.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    PictureService pictureService;

    @GetMapping("/{id}")
    public String indexUser(@PathVariable("id") Long id, Model model) {
        mockLogin(model, id);

        User user = userService.findById(2L); //todo change it by the session (user.id)
        model.addAttribute("user", user);
        return "index";
    }

    private void mockLogin(Model model, Long id) {
        model.addAttribute("user", userService.findById(id));
    }


    @GetMapping("/")
    public String index(Model model) {
        mockLogin(model, 1l);

        User user = userService.findById(2L); //todo change it by the session (user.id)

        List<Product> productList = productService.getAll();
        List<ProductInfo> products = new ArrayList<ProductInfo>();

        if (productList != null) {
            for (Product product : productList) {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setId(product.getId());
                productInfo.setName(product.getName());
                productInfo.setIsAnAdd(product.getIsAnAdd());

                int rating = reviewService.findByProductId(product.getId()).size() > 0 ? reviewService.getAverageRating(product.getId()) : 0;
                productInfo.setRating(rating);

                String SEPARATOR = "";
                List<Category> categories = product.getCategories();
                StringBuilder categoryNames = new StringBuilder();
                for (Category category : categories) {
                    categoryNames.append(SEPARATOR);
                    categoryNames.append(category.getName());
                    SEPARATOR = ", ";
                }

                productInfo.setCategories(categoryNames.toString());

                productInfo.setUnitPrice(product.getUnitPrice());
                productInfo.setPicture(pictureService.findByProductId(product.getId()).get(0).getFileName()); //todo change it to pictures
                products.add(productInfo);
            }
        }

        model.addAttribute("products", products);

        List<ProductInfo> ads = products.stream().filter(ProductInfo::getIsAnAdd).collect(Collectors.toList());

        model.addAttribute("ads", ads);

        model.addAttribute("user", user);

        return "index";
    }



}
