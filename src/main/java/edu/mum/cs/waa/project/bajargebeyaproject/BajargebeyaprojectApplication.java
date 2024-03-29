package edu.mum.cs.waa.project.bajargebeyaproject;

import com.sun.mail.util.MailSSLSocketFactory;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.*;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.security.GeneralSecurityException;
import java.time.LocalDate;

@SpringBootApplication
public class BajargebeyaprojectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BajargebeyaprojectApplication.class, args);
//        dataLoader(context);
//        sendMailCheck(context);
    }

    private static void sendMailCheck(ApplicationContext context) {
        MailService mailService = context.getBean(MailService.class);
        try {
            Email email = new Email();
            email.setSubject("Application starts");
            email.setMessage("App has started");
            email.addReceiver("sujiv.shrestha@mum.edu");
//            email.addReceiver("sujiv.sth@outlook.com");
//            email.addReceiver("sujiv.sth@gmail.com");
            mailService.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public JavaMailSender getJavaMailSender() throws GeneralSecurityException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.office365.com");//"smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("sujiv.sth@outlook.com");
//        mailSender.setPassword("Suzeve76");
//
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        System.setProperty("javax.net.ssl.trustStore", "E:/");
//        System.setProperty("javax.net.ssl.trustStore", "C:/Program Files/Java/jdk1.7.0_17/jre/lib/security/cacerts");
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.imap.ssl.trust", "*");
//        props.put("mail.imap.ssl.socketFactory", sf);
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//        props.put("mail.smtp.host", "smtp.outlook.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.user", "sujiv.sth@outlook.com");
//        props.put("mail.password", "Suzeve76");

        return mailSender;
    }

    public static void dataLoader(ApplicationContext context){
        UserService userService = context.getBean(UserService.class);
        ProductService productService = context.getBean(ProductService.class);
        CartService cartService = context.getBean(CartService.class);
        ReviewService reviewService = context.getBean(ReviewService.class);
        PaymentService paymentService = context.getBean(PaymentService.class);
        NotificationService notificationService = context.getBean(NotificationService.class);

        User us = new User();
        Account acc = new Account();
        acc.setCardType("Credit");
        acc.setCsv("225");
       // acc.setExpiryDate(LocalDate.now());
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
        s.setIsApproved(true);
        s.setUser(us);
        s = userService.saveSeller(s);

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
        b.setCart(new Cart());
        s.getFollowers().add(b);
        b = userService.saveBuyer(b);
        b.getFollowings().add(s);

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
        Admin a = new Admin();
        a.setUser(ua);
        a = userService.saveAdmin(a);

        addUser(userService,"Sujiv", "Admin",add);
        addUser(userService,"Saroj", "Seller",add);
        addUser(userService,"Sanjay", "Seller",add);
        addUser(userService,"Surafel", "Seller",add);

        Category c = new Category();
        c.setName("Electronics");
        c = productService.saveCategory(c);

        Product p = addProduct(productService, us, s, c, "HP");

        Product p2 = addProduct(productService, us, s, c, "Dell");

        Image i = new Image();
        i.setProduct(p);
        i.setUrl("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6240/6240848_sd.jpg");

        Image i2 = new Image();
        i2.setProduct(p);
        i2.setUrl("https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6240/6240848_rd.jpg");

        p.getImages().add(i);
        p.getImages().add(i2);

        i = productService.saveImage(i);
        i2 = productService.saveImage(i2);

        Review rv = new Review();
        rv.setIsApproved(true);
        rv.setDate(LocalDate.now());
        rv.setDescription("Good Product");
        rv.setRating(5);
        rv.setProduct(p);
        p.getReviews().add(rv);
        rv.setBuyer(b);
        rv = reviewService.save(rv);

        Cart ca = b.getCart();
        CartEntry ce = new CartEntry(ca);
        ce.setQuantity(2);
        ce.setCart(ca);
        ce.setProduct(p);
        ca.getCartEntries().add(ce);

//        ca.setBuyer(b);
        cartService.saveCart(ca);

        ProductOrder po = new ProductOrder();
        po.setOrderDate(LocalDate.now());
//        po.setQuantity(2);
        po.setShippingAddress(add);
        po.setStatus("delivered");
//        po.getProducts().add(p);
        po.setBuyer(b);
        b.getProductOrders().add(po);
        po = productService.saveOrder(po);

//        Receipt rc = new Receipt();
//        rc.setProductOrder(po);
//        double total = 0.0;
//        for(CartEntry ce: po.getCartEntries()){
//            Product p =
//            ReceiptEntry re = new ReceiptEntry();
//            re.setDiscount(pr.getDiscount());
//            re.setPrice(pr.getUnitPrice());
//            re.setProductName(pr.getName());
//            re.setQuantity(1);
//            re.setTax(pr.getTax());
//            re.setReceipt(rc);
//            rc.getReceiptEntries().add(re);
//            total+=pr.getUnitPrice()*(1-pr.getDiscount())*(1+pr.getTax());
//        }
//        rc.setTotal(total);
//        po.setReceipt(rc);
//        rc = paymentService.saveReceipt(rc);

        notificationService.notifyAll("Welcome to Awesome Shopping Site!","www.google.com");
        notificationService.notifyAdmins("Be alert of Hackers!","www.stackoverflow.com");
        notificationService.notifyBuyers("Cheap Sales. Hurry up","www.github.com");
        notificationService.notifySellers("Buyers out on market!","www.amazon.com");
        notificationService.notify("Test notice","/",b.getUser());
    }

    private static User addUser(UserService us, String fn, String rol, Address add) {
        User ub = new User();
//        ub.setAccount(acc);
        ub.setBillingAddress(add);
        ub.setEmail("pqr@mum.edu");
        ub.setFirstName(fn);
        ub.setLastName("user");
        ub.setMailingAddress(add);
        ub.setPassword("");
        Role rb = new Role();
        rb.setRole(rol);
        ub.setRole(rb);
        switch (rol) {
            case "Admin":
                Admin a = new Admin();
                a.setUser(ub);
                us.saveAdmin(a);
                ub = a.getUser();
                break;
            case "Buyer":
                Buyer b = new Buyer();
                b.setUser(ub);
                b.setCart(new Cart());
                b.setReward(0);
                us.saveBuyer(b);
                ub = b.getUser();
                break;
            case "Seller":
                Seller s = new Seller();
                s.setIsApproved(false);
                s.setUser(ub);
                us.saveSeller(s);
                ub = s.getUser();
            default:
        }
        return ub;
    }

    private static Product addProduct(ProductService productService, User us, Seller s, Category c, String name) {
        Product p = new Product();
        p.setIsAnAdd(false);
        p.setDescription("HP Laptop");
        p.setDiscount(0.0);
        p.setTax(0.0);
        p.setName(name);
        p.setUnitPrice(100.0);
        p.setUnit("pcs.");
        p.setStock(5);
        p.setSeller(us);
        s.getProducts().add(p);
        c.getProducts().add(p);
        p.getCategories().add(c);
        productService.save(p);
        return p;
    }
}
