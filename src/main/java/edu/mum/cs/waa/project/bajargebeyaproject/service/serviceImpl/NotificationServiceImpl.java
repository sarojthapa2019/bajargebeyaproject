package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.repository.NotificationRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Notification;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;
import edu.mum.cs.waa.project.bajargebeyaproject.service.NotificationService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.PaymentService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.ProductService;
import edu.mum.cs.waa.project.bajargebeyaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public boolean notify(String noteMsg, String actionUrl, User target) {
        Notification n = new Notification();
        n.setDate(LocalDate.now());
        n.setMessage(noteMsg);
        n.setPriority(0);
        n.getReceivers().add(target);
        n.setActionUrl(actionUrl);
        target.getNotifications().add(n);
        return (notificationRepo.save(n)!=null);
    }

    @Override
    public boolean notifyUsers(String noteMsg, String actionUrl, List<User> users) {
        boolean notified = false;
        for(User target: users){
            notified = notify(noteMsg,actionUrl,target);
            if(!notified)
                return false;
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
}
