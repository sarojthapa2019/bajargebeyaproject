package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Email;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.NotificationRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Notification;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    MailService mailService;

    @Override
    public Notification buildNotification(String noteMsg, String actionUrl) {
        Notification note = new Notification();
        note.setMessage(noteMsg);
        note.setActionUrl(actionUrl);
        note.setDate(LocalDate.now());
        note.setPriority(0);
        return note;
    }

    @Override
    public boolean notify(Notification notification, User target) {
        notification.addReceiver(target);
        notification = notificationRepo.save(notification);
        target.addNotification(notification);
        userService.save(target);
        System.out.println(target.toString()+notification.getMessage());
//        notification.addReceiver(target);
        return notification.getReceivers().contains(target);
    }

    @Override
    public boolean notify(String noteMsg, String actionUrl, User target) {
        Notification n = buildNotification(noteMsg, actionUrl);
        n = notificationRepo.save(n);
        target.addNotification(n);
        userService.save(target);
        return true;
    }

    @Override
    public boolean notifyUsers(String noteMsg, String actionUrl, List<User> users) {
        boolean notified = false;
        Notification n = buildNotification(noteMsg,actionUrl);
        for(User target: users){
            notified = notify(n,target);
            if(!notified)
                return false;
            System.out.println("Notifying... "+notified);
        }

        return true;
    }

    @Override
    //Adds notification to all users in userTable
    public boolean notifyAll(String noteMsg, String actionUrl) {
        return notifyUsers(noteMsg, actionUrl, (List)userService.findAll());
    }

    @Override
    //Adds notification to all users by role type in userTable
    public boolean notifyByRole(String noteMsg, String actionUrl, String role) {
        return notifyUsers(noteMsg, actionUrl, userService.findAllByRole(role));
    }

    @Override
    public boolean notifyAdmins(String noteMsg, String actionUrl) {
        return notifyByRole(noteMsg, actionUrl, "Admin");
    }

    @Override
    public boolean notifySellers(String noteMsg, String actionUrl) {
        return notifyByRole(noteMsg, actionUrl, "Seller");
    }

    @Override
    public boolean notifyBuyers(String noteMsg, String actionUrl) {
        return notifyByRole(noteMsg, actionUrl, "Buyer");
    }

    @Override
    public boolean notifySujiv(String message) {
        Email email = new Email();
        email.setMessage(message);
        email.setSubject("Bajar Activity Notification");
        email.setReceivers(Arrays.asList("sujiv.shrestha@mum.edu"));
        try {
            mailService.sendMail(email);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void sendReceipt(String sub, String file, User target) throws Exception {
        Email email = new Email();
        email.addReceiver(target.getEmail());
        email.setMessage("Thank you for buying at BajarGebeya. Keep buying");
        email.setSubject(sub);
        email.setFilePath(file);
        mailService.sendMail(email);
    }

    @Override
    public void sendReceipt(Long id, User user) throws Exception {
        Email email = new Email();
        email.addReceiver(user.getEmail());
        email.setMessage("Thank you for buying at BajarGebeya. Keep buying. Please login and goto http://localhost:8080/printReceipt/"+id+" to print your receipt.");
        email.setSubject("Your Purchase Receipt: "+id);
        mailService.sendMail(email);
    }
}
