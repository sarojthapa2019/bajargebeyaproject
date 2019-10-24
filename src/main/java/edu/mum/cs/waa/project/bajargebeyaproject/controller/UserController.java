package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService
//            , PasswordEncoder passwordEncoder
    ){
        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
    }
//
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }


//    @RequestMapping("/user/login")
//    public String login(){
//        // User doesn't need to re-enter credentials
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if  (auth instanceof AnonymousAuthenticationToken)  {
//            System.out.println("anonymous user");
//            return "login";
//
//
//        } else {
//            return "redirect:/";
//        }
//    }

    @RequestMapping(value="/user/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", userService.getAllRole());
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

            user.setPassword(user.getPassword());
//        passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            System.out.println("user :"+user +" registered !!!!!");
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }
}
