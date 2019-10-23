package edu.mum.cs.waa.project.bazargebeyaproject.controller;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Category;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.ProductInfo;
import edu.mum.cs.waa.project.bazargebeyaproject.service.PictureService;
import edu.mum.cs.waa.project.bazargebeyaproject.service.ProductService;
import edu.mum.cs.waa.project.bazargebeyaproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"user"})
public class UserController {

    @Autowired
    ProductService productService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    PictureService pictureService;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> productList = productService.getAll();
        List<ProductInfo> products = new ArrayList<ProductInfo>();

        if (productList != null) {
            for (Product product : productList) {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setId(product.getId());
                productInfo.setName(product.getName());
                productInfo.setIsAnAdd(product.getIsAnAdd());

                int rating =  reviewService.findByProductId(product.getId()).size() > 0 ? reviewService.getAverageRating(product.getId()): 0;
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
        return "index";
    }
}
