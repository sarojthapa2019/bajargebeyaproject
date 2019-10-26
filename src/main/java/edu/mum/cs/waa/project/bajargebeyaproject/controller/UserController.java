package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
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
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes({"user","cart","itemCount"})
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
        User user = userService.findById(2L); //todo change it by the session (user.id)
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        //User user = userService.findById(2L); //todo change it by the session (user.id)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByEmail(auth.getName());
        model.addAttribute("user", user.get());

        if(user.get().getRole().getRole().equals("ROLE_ADMIN") || user.get().getRole().getRole().equals("ROLE_SELLER")){
            return "redirect:/dashboard";
        }

        System.out.println("User****" + user.get());

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

        Buyer buyer = (Buyer)userService.getBuyerByUserId(user.get().getId());
        Cart cart = buyer.getCart();

        model.addAttribute("cart", cart);

        if(user.get().getRole().getRole().equals("ROLE_BUYER")) {

            model.addAttribute("cart", userService.getBuyerByUserId(user.get().getId()).getCart());
            model.addAttribute("itemCount", userService.getBuyerByUserId(user.get().getId()).getCart().getTotalItems());
        }

        return "index";
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
//
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }


    @RequestMapping("/user/login")
    public String login(Model model){
        // User doesn't need to re-enter credentials
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if  (auth instanceof AnonymousAuthenticationToken)  {
            System.out.println("anonymous user");
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value="/user/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        List<Role> roles = userService.getAllRole();
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> userExists = userService.findByEmail(user.getEmail());
        if(userExists.isPresent()){
            System.out.println("User exists");
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            System.out.println("registration form has errors !!!!!!!!!!!!!");
            modelAndView.setViewName("registration");
        }
        else {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            System.out.println("user :"+user +" registered !!!!!");
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }
}
