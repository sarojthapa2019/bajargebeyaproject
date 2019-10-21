package edu.mum.cs.waa.project.bazargebeyaproject.service;

import edu.mum.cs.waa.project.bazargebeyaproject.domain.User;

import java.util.List;

public interface NotificationService {

    public boolean notify(String noteMsg, String actionUrl, User target);

    public boolean notifyUsers(String noteMsg, String actionUrl, List<User> users);

    public boolean notifyAll(String noteMsg, String actionUrl);

    public boolean notifyByRole(String noteMsg, String actionUrl, String role);

    public boolean notifyAdmins(String noteMsg, String actionUrl);

    public boolean notifySellers(String noteMsg, String actionUrl);

    public boolean notifyBuyers(String noteMsg, String actionUrl);

}