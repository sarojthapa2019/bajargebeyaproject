package edu.mum.cs.waa.project.bajargebeyaproject;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class BajargebeyaprojectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BajargebeyaprojectApplication.class, args);
        //dataLoader(context);
    }

    public static void dataLoader(ApplicationContext context){
        UserService userService = context.getBean(UserService.class);
        ProductService productService = context.getBean(ProductService.class);
        //NotificationService notificationService = context.getBean(NotificationService.class);

        User us = userService.findById(1l);
//        Account acc = new Account();
//        acc.setCardType("Credit");
//        acc.setCsv("225");
//        acc.setExpiryDate(LocalDate.now());
//        acc.setNumber("12345678");
//        us.setAccount(acc);
//        Address add = new Address();
//        add.setCity("Fairfield");
//        add.setCountry("USA");
//        add.setState("IOWA");
//        add.setStreet("1000N 4th Street");
//        add.setZipCode("52557");
//        us.setBillingAddress(add);
//        us.setEmail("xyz@mum.edu");
//        us.setFirstName("Seller");
//        us.setLastName("user");
//        us.setMailingAddress(add);
//        us.setPassword("");
//        Role r = new Role();
//        r.setRole("Seller");
//        us.setRole(r);

        User ub = userService.findById(3l);
//                new User();
//        ub.setAccount(acc);
//        ub.setBillingAddress(add);
//        ub.setEmail("pqr@mum.edu");
//        ub.setFirstName("Buyer");
//        ub.setLastName("user");
//        ub.setMailingAddress(add);
//        ub.setPassword("");
//        Role rb = new Role();
//        rb.setRole("Seller");
//        ub.setRole(rb);

        User ua = userService.findById(2l);
//                new User();
//        ua.setAccount(acc);
//        ua.setBillingAddress(add);
//        ua.setEmail("abc@mum.edu");
//        ua.setFirstName("Admin");
//        ua.setLastName("user");
//        ua.setMailingAddress(add);
//        ua.setPassword("admin");
//        Role ra = new Role();
//        ra.setRole("admin");
//        ua.setRole(ra);

        Product p = new Product();
        p.setAnAdd(false);

        Category c = new Category();
        c.setName("Electronics");
        c.getProducts().add(p);

        p.getCategories().add(c);
        p.setDescription("HP Laptop");
        p.setDiscount(0.0);
        p.setTax(0.0);

        Image i = new Image();
        i.setProduct(p);
        i.setUrl("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6240/6240848_sd.jpg");

        Image i2 = new Image();
        i2.setProduct(p);
        i.setUrl("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6240/6240848_rd.jpg");

        p.getImages().add(i);
        p.getImages().add(i2);
        p.setName("Laptop");

        Review rv = new Review();
        rv.setApproved(true);

        Buyer b = new Buyer();

        Cart ca = new Cart();

        ca.setBuyer(b);
        ca.getProducts().add(p);

        b.setCart(ca);

        Seller s = new Seller();
        s.setApproved(true);
        s.getFollowers().add(b);
        s.getProducts().add(p);
        s.setUser(us);

        b.getFollowings().add(s);
        b.setReward(100);
        b.setUser(ub);

        ProductOrder po = new ProductOrder();
        po.setBuyer(b);
        po.setOrderDate(LocalDate.now());
        po.getProducts().add(p);
        po.setQuantity(2);

        Receipt rc = new Receipt();
        rc.setProductOrder(po);

        ReceiptEntry re = new ReceiptEntry();
        re.setDiscount(p.getDiscount());
        re.setPrice(p.getUnitPrice());
        re.setProductName(p.getName());
        re.setQuantity(1);
        re.setReceipt(rc);
        re.setTax(p.getTax());

        rc.getReceiptEntries().add(re);

        po.setReceipt(rc);

        b.getProductOrders().add(po);

        rv.setBuyer(b);

        p.getReviews().add(rv);
        p.setUnit("pcs.");
        p.setSeller(us);
        p.setStock(5);

//        userService.save(ua);
//        userService.save(ub);
//        userService.save(us);

//        productService.save(p);
//        productService.saveOrder(po);
    }

}