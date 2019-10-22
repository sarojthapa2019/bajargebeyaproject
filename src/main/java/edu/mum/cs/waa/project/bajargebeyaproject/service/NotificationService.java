package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Notification;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;

import java.util.List;

public interface NotificationService {

    public Notification buildNotification(String noteMsg, String actionUrl);

    public boolean notify(Notification notification, User target);

    public boolean notify(String noteMsg, String actionUrl, User target);

    public boolean notifyUsers(String noteMsg, String actionUrl, List<User> users);

    public boolean notifyAll(String noteMsg, String actionUrl);

    public boolean notifyByRole(String noteMsg, String actionUrl, String role);

    public boolean notifyAdmins(String noteMsg, String actionUrl);

    public boolean notifySellers(String noteMsg, String actionUrl);

    public boolean notifyBuyers(String noteMsg, String actionUrl);

}
