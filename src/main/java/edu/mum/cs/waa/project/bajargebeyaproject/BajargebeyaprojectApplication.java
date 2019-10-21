package edu.mum.cs.waa.project.bajargebeyaproject;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class BajargebeyaprojectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BajargebeyaprojectApplication.class, args);
        dataLoader(context);
    }

    public static void dataLoader(ApplicationContext context){
        UserService userService = context.getBean(UserService.class);
        ProductService productService = context.getBean(ProductService.class);
        CartService cartService = context.getBean(CartService.class);
        ReviewService reviewService = context.getBean(ReviewService.class);
        PaymentService paymentService = context.getBean(PaymentService.class);
        //NotificationService notificationService = context.getBean(NotificationService.class);

        User us = new User();
        Account acc = new Account();
        acc.setCardType("Credit");
        acc.setCsv("225");
        acc.setExpiryDate(LocalDate.now());
        acc.setNumber("12345678");
        us.setAccount(acc);
        Address add = new Address();
        add.setCity("Fairfield");
        add.setCountry("USA");
        add.setState("IOWA");
        add.setStreet("1000N 4th Street");
        add.setZipCode("52557");
        us.setBillingAddress(add);
        us.setEmail("xyz@mum.edu");
        us.setFirstName("Seller");
        us.setLastName("user");
        us.setMailingAddress(add);
        us.setPassword("");
        Role r = new Role();
        r.setRole("Seller");
        us.setRole(r);
        Seller s = new Seller();
        s.setApproved(true);
        s.setUser(us);
        userService.saveSeller(s);
//        userService.save(us);

        User ub = new User();
        ub.setAccount(acc);
        ub.setBillingAddress(add);
        ub.setEmail("pqr@mum.edu");
        ub.setFirstName("Buyer");
        ub.setLastName("user");
        ub.setMailingAddress(add);
        ub.setPassword("");
        Role rb = new Role();
        rb.setRole("Buyer");
        ub.setRole(rb);
        Buyer b = new Buyer();
        b.setReward(100);
        b.setUser(ub);
        userService.saveBuyer(b);
        s.getFollowers().add(b);
        b.getFollowings().add(s);
        //user_seller, user_buyer, buyer, user_admin, product, category, image1, image2
        //user_seller, user_buyer

        User ua = new User();
        ua.setAccount(acc);
        ua.setBillingAddress(add);
        ua.setEmail("abc@mum.edu");
        ua.setFirstName("Admin");
        ua.setLastName("user");
        ua.setMailingAddress(add);
        ua.setPassword("Admin");
        Role ra = new Role();
        ra.setRole("Admin");
        ua.setRole(ra);
//        userService.save(ua);
        Admin a = new Admin();
        a.setUser(ua);
        userService.saveAdmin(a);
        //user_seller, user_buyer, user_admin

        Product p = new Product();
        p.setAnAdd(false);
        p.setDescription("HP Laptop");
        p.setDiscount(0.0);
        p.setTax(0.0);
        p.setName("Laptop");
        p.setUnit("pcs.");
        p.setStock(5);
        productService.save(p);
        s.getProducts().add(p);
        p.setSeller(us);
        //user_seller, user_buyer, user_admin, product

        Category c = new Category();
        c.setName("Electronics");
        c.getProducts().add(p);
        p.getCategories().add(c);
        productService.saveCategory(c);
        //user_seller, user_buyer, user_admin, product, category

        Image i = new Image();
        i.setProduct(p);
        i.setUrl("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6240/6240848_sd.jpg");

        Image i2 = new Image();
        i2.setProduct(p);
        i.setUrl("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6240/6240848_rd.jpg");

        p.getImages().add(i);
        p.getImages().add(i2);

        productService.saveImage(i);
        productService.saveImage(i2);
        //user_seller, user_buyer, user_admin, product, category, image1, image2


        Review rv = new Review();
        rv.setApproved(true);
        rv.setDate(LocalDate.now());
        rv.setDescription("Good Product");
        rv.setRating(5);
        reviewService.save(rv);
        rv.setProduct(p);
        p.getReviews().add(rv);
        rv.setBuyer(b);

        Cart ca = new Cart();
        b.setCart(ca);
        cartService.saveCart(ca);
        ca.setBuyer(b);
        ca.getProducts().add(p);

        ProductOrder po = new ProductOrder();
        po.setOrderDate(LocalDate.now());
        po.setQuantity(2);
        po.setShippingAddress(add);
        po.setStatus("delivered");
        productService.saveOrder(po);
        po.getProducts().add(p);
        po.setBuyer(b);
        b.getProductOrders().add(po);

        Receipt rc = new Receipt();
        rc.setProductOrder(po);
        for(Product pr: po.getProducts()){
            ReceiptEntry re = new ReceiptEntry();
            re.setDiscount(pr.getDiscount());
            re.setPrice(pr.getUnitPrice());
            re.setProductName(pr.getName());
            re.setQuantity(1);
            re.setTax(pr.getTax());
            re.setReceipt(rc);
            rc.getReceiptEntries().add(re);
        }
        paymentService.saveReceipt(rc);
        po.setReceipt(rc);

    }
}
