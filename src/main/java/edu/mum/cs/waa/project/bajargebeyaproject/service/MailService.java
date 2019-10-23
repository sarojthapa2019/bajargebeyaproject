package edu.mum.cs.waa.project.bajargebeyaproject.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface MailService {
    public void sendMail() throws AddressException, MessagingException, IOException;
    public void sendMail2();
}
