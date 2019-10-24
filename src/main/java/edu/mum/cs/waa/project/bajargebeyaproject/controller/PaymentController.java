package edu.mum.cs.waa.project.bajargebeyaproject.controller;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.PaymentDetail;
import edu.mum.cs.waa.project.bajargebeyaproject.service.CartService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.PaymentService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/payment")
@SessionAttributes({"user","itemSize","carts"})
public class PaymentController {
    private final String base = "/payment";

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public String pay(Model model){
//        model.addAttribute("amount",100.0);
//        model.addAttribute("payView","/payment/pay");
//        return "payenable";
//    }

    @GetMapping("/pay/{amount}")
    public RedirectView pay(@PathVariable("amount") Double total,
//            @ModelAttribute("userId") String userId,
            Model model){
//        Double total = 0.0;
        String product="BajarGebeya";

        System.out.println("Total:" + total);
        model.addAttribute("total", total);
        String url = paymentService.makePayment(total, product);
        System.out.println(url);
        RedirectView redir = new RedirectView();
        redir.setUrl(url);
        return redir;
    }
    @GetMapping("/cancel")
    public String cancelled(){
        return "pay_cancel";
    }

    @GetMapping("/success")
    public String success(@ModelAttribute("userId") String userId, @RequestParam String paymentId, @RequestParam String token, @RequestParam String PayerID, Model model){
        System.out.println("Payment ID:"+paymentId);
        model.addAttribute("paymentId",paymentId);
        model.addAttribute("token",token);
        model.addAttribute("PayerID",PayerID);

        PaymentDetail pd = new PaymentDetail();
        try {
            pd = paymentService.logTransaction(paymentId,token,PayerID);
//            if(cartService.getReceipt())
                System.out.println("Payment Details added");
            model.addAttribute("message"," Thank you for shopping at BajarGebeya");
        } catch (JSONException e) {
            e.printStackTrace();
            model.addAttribute("message",e.getLocalizedMessage());
        }


        model.addAttribute("pd",pd);
        return "pay_success";
    }
}
