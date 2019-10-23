package edu.mum.cs.waa.project.bajargebeyaproject.service;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Email;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.User;

public interface MailService {
    public void sendMail(Email email) throws Exception;
    public void sendMail(User receiver, String subject, String message);
}
