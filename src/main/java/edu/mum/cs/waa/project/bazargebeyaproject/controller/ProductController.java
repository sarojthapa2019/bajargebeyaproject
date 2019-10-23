package edu.mum.cs.waa.project.bazargebeyaproject.controller;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.Category;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Product;
import edu.mum.cs.waa.project.bazargebeyaproject.domain.Review;
import edu.mum.cs.waa.project.bazargebeyaproject.service.CategoryService;
import edu.mum.cs.waa.project.bazargebeyaproject.service.PictureService;
import edu.mum.cs.waa.project.bazargebeyaproject.service.ProductService;
import edu.mum.cs.waa.project.bazargebeyaproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    PictureService pictureService;

    @Autowired
    ReviewService reviewService;

    /*Name: product related routes
     * Author: Fekadu
     * Date: October 22, 2019
     * */
    @GetMapping("/")
    public String getProductForm(@ModelAttribute("product") Product product, Model model){

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAll());

        return "productform";
    }

    @PostMapping("/add")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes){
        Product savedProduct = productService.save(product);

        if(savedProduct!=null) {
            redirectAttributes.addAttribute("message", "Category added successfully.");
        }else {
            redirectAttributes.addAttribute("error", "Category cannot be saved now. Please try again.");
        }

        return "redirect:/products/";
    }

    @PostMapping("/update")
    public String updateProduct(Product product, RedirectAttributes redirectAttributes){
        Product updatedProduct = productService.save(product);

        if(updatedProduct!=null) {
            redirectAttributes.addAttribute("message", "Product updated successfully.");
        }else {
            redirectAttributes.addAttribute("error", "Product cannot be updated now. Please try again.");
        }

        return "redirect:/products/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model){
        productService.delete(id);

        model.addAttribute("message", "Category deleted successfully.");

        return "redirect:/products/";
    }

    @PostMapping("/{id}/reviews/add")
    public String addReview(Review review, @PathVariable("id") Long id, RedirectAttributes redirectAttributes){

        Product product = productService.findById(id);

        LocalDate date = LocalDate.now();
        review.setDate(date);
        review.setIsApproved(false);
        review.setProduct(product);

        Review savedReview = reviewService.save(review);

        if(savedReview!=null) {
            redirectAttributes.addAttribute("message", "Review added successfully.");
        }else {
            redirectAttributes.addAttribute("error", "Review cannot be added now. Please try again.");
        }

        return "redirect:/products/"+ id;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") Long id, @ModelAttribute("review") Review review, Model model){
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("pictures", pictureService.findByProductId(id));
        model.addAttribute("reviews", reviewService.findByProductId(id));

        return "product";
    }

    /*Name: category related routes
    * Author: Fekadu
    * Date: October 22, 2019
    * */

    @GetMapping("/category")
    public String getCategoryForm(@ModelAttribute ("category")Category category, Model model){
        model.addAttribute("categories", categoryService.getAll());

        return "categoryform";
    }

    @PostMapping("/category/add")
    public String saveCategory(Category category, RedirectAttributes redirectAttributes){
        Category savedCategory = categoryService.save(category);

        if(savedCategory!=null) {
            redirectAttributes.addAttribute("message", "Category added successfully.");
        }else {
            redirectAttributes.addAttribute("error", "Category cannot be saved now. Please try again.");
        }

        return "redirect:/products/category";
    }

    @PostMapping("/category/update")
    public String updateCategory(Category category, RedirectAttributes redirectAttributes){
        Category updatedCategory = categoryService.save(category);

        if(updatedCategory!=null) {
            redirectAttributes.addAttribute("message", "Category updated successfully.");
        }else {
            redirectAttributes.addAttribute("error", "Category cannot be updated now. Please try again.");
        }

        return "redirect:/products/category";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model){
        categoryService.delete(id);

        model.addAttribute("message", "Category deleted successfully.");

        return "redirect:/products/category";
    }
}
